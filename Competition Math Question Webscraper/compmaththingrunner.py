from collections import defaultdict
import pickle
import random
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import asyncio
from playwright.async_api import async_playwright


class Problem:
    def __init__(self, genre, difficulty, question, link):
        self.difficulty = difficulty
        self.genre = genre
        self.question = question
        self.link = link


async def html_to_image(question):
    async with async_playwright() as p:
        browser = await p.chromium.launch()
        page = await browser.new_page()
        await page.set_viewport_size({'width': 400, 'height': 240})
        await page.set_content(question)
        await page.screenshot(path='question.png')
        await browser.close()


def display_question(genre, difficulty):
    length = len(updated_problems_db[genre][difficulty])
    random_index = random.randint(0, length - 1)
    asyncio.run(html_to_image(updated_problems_db[genre][difficulty][random_index].question))




    img = mpimg.imread('question.png')


    plt.imshow(img)
    plt.axis('off')  # Turn off axis labels
    print(updated_problems_db[genre][difficulty][random_index].link)
    plt.show()


    return random_index


def convert_genre_and_difficulty(this_genre, this_difficulty):
    genre = ''
    difficulty = ''
   
    if (this_genre == 'a'):
        genre = 'Algebra'
    elif (this_genre == 'c'):
        genre = 'Combinatorics'
    elif (this_genre == 'g'):
        genre = 'Geometry'
    elif (this_genre == 'n'):
        genre = 'Number'
    elif (this_genre == 'p'):
        genre = 'Probability'
    else:
        genre = 'Trigonometry'


    if (this_difficulty == 'intro'):
        difficulty = "Introductory"
    elif (this_difficulty == 'inter'):
        difficulty = "Intermediate"
    else:
        difficulty = "Olympiad"


    return genre, difficulty


with open('updated_problems_db.pkl','rb') as f:
    updated_problems_db = pickle.load(f)


with open('correct_problems_db.pkl','rb') as f:
    correct_problems_db = pickle.load(f)


a = True




while a:
    print("What genre of question would you like?")
    b = True
    while b:
        genre = input("\nEnter 'a' for Algebra\nEnter 'c' for Combinatorics\nEnter 'g' for Geometry\nEnter 'n' for Number Theory\nEnter 'p' for Probability\nEnter 't' for Trigonometry\n")
        if genre not in ('a', 'c', 'g', 'n', 'p', 't'):
            print("That is not in the accepted inputs. Please try again.")
        else:
            b = False
    print("What difficulty of question would you like?")
    b = True
    while b:
        difficulty = input("\nEnter 'intro' for Introductory\nEnter 'inter' for Intermediate\nEnter 'o' for Olympiad\n")
        if difficulty not in ('intro', 'inter', 'o'):
            print("That is not in the accepted inputs. Please try again.")
        else:
            b = False
    genre, difficulty = convert_genre_and_difficulty(genre, difficulty)
    index = display_question(genre, difficulty)
    print("Did you get that answer correct?")
    b = True
    while b:
        correct = input("\nEnter 'y' for yes\nEnter 'n' for no\n")
        if correct not in ('y', 'n'):
            print("That is not in the accepted inputs. Please try again.")
        else:
            b = False
    if correct == 'y':
        correct_problems_db[genre][difficulty].append(updated_problems_db[genre][difficulty][index])
        del updated_problems_db[genre][difficulty][index]
    print("Would you like to answer another question?")
    answer = input("\nEnter 'y' for yes\nEnter 'n' for no\n")
    if answer == 'n':
        a = False


correct_problems_db = {k: dict(v) for k, v in correct_problems_db.items()}


with open('updated_problems_db.pkl', 'wb') as f:
    pickle.dump(updated_problems_db, f)


with open('correct_problems_db.pkl', 'wb') as f:
    pickle.dump(correct_problems_db, f)