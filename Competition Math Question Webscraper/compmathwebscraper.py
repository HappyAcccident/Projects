from bs4 import BeautifulSoup
import requests
import time
import re
import pickle
from collections import defaultdict

def convert_to_absolute_links(input_string):
    pattern = r'(?<=src=")//'
    output_string = re.sub(pattern, 'https://', input_string)
    return output_string

def extract_question(soup):
    question_tag = soup.find('p')
    if question_tag is None:
        return None

    question = convert_to_absolute_links(str(question_tag))
    next_sibling = question_tag.find_next_sibling()
    while next_sibling and next_sibling.name != 'h2':
        if next_sibling.name == 'p':
            question += "\n" + convert_to_absolute_links(str(next_sibling))
        next_sibling = next_sibling.find_next_sibling()

    return question

class Problem:
    def __init__(self, genre, difficulty, question, link):
        self.difficulty = difficulty
        self.genre = genre
        self.question = question
        self.link = link

def fetch_url(url, retries=3, timeout=10):
    for _ in range(retries):
        try:
            response = requests.get(url, timeout=timeout)
            response.raise_for_status()
            return response
        except requests.exceptions.RequestException as e:
            print(f"Request failed: {e}. Retrying...")
            time.sleep(2)
    return None

problems_db = defaultdict(lambda: defaultdict(list))

page = fetch_url("https://artofproblemsolving.com/wiki/index.php/Category:Math_Problems")
if page:
    soup = BeautifulSoup(page.text, "html.parser")

    tags = soup.find_all('div', id='mw-subcategories')
    for tag in tags:
        links = tag.find_all('a')
        for link in links:
            genre = link.text.split(' ')[0]
            problems = "https://artofproblemsolving.com" + link.get('href')
            page1 = fetch_url(problems)
            if page1:
                soup1 = BeautifulSoup(page1.text, "html.parser")
                tags1 = soup1.find_all('div', id='mw-content-text')
                for tag1 in tags1:
                    links1 = tag1.find_all('a')
                    for link1 in links1:
                        if "Category" in link1.get('href'):
                            difficulty = link1.text.split(' ')[0]
                            problems1 = "https://artofproblemsolving.com" + link1.get('href')
                            page2 = fetch_url(problems1)
                            if page2:
                                a = True
                                while a:
                                    soup2 = BeautifulSoup(page2.text, "html.parser")
                                    tags2 = soup2.find_all('div', id='mw-pages')
                                    for tag2 in tags2:
                                        links2 = tag2.find_all('a')
                                        for link2 in links2:
                                            if "pagefrom" in link2.get('href'):
                                                page2 = fetch_url("https://artofproblemsolving.com" + link2.get('href'))
                                                a = True
                                            elif "pageuntil" not in link2.get('href'):
                                                a = False
                                                link = "https://artofproblemsolving.com" + link2.get('href')
                                                page3 = fetch_url(link)
                                                if page3:
                                                    soup3 = BeautifulSoup(page3.text, "html.parser")
                                                    tags3 = soup3.find_all('div', class_='mw-parser-output')
                                                    for tag3 in tags3:
                                                        question = extract_question(tag3)
                                                        problem = Problem(genre, difficulty, question, link)
                                                        problems_db[genre][difficulty].append(problem)
                                                        print(genre)
                                                        print(difficulty)
                                                        print(problem.question)
                                                        print(link)

# Convert the defaultdict to a regular dictionary
problems_db = {k: dict(v) for k, v in problems_db.items()}

with open('problems_db.pkl', 'wb') as f:
    pickle.dump(problems_db, f)

print("Problems saved successfully.")