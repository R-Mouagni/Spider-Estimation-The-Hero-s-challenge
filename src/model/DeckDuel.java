package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a deck of questions for a duel in the special card Duel
 */
public class DeckDuel {
	private List<Question> questionsDuel;
	
    /**
     * Constructs a new DeckDuel object and initializes it with a set of predefined questions
     */
	public DeckDuel() {
		questionsDuel = new ArrayList<>();

		questionsDuel.add(new Question("What is the capital of France?", "Paris"));

        questionsDuel.add(new Question("What is the color of the sky?", "Blue"));

        questionsDuel.add(new Question("What is the capital of Spain?", "Madrid"));

        questionsDuel.add(new Question("How many planets are there in the solar system?", "8"));

        questionsDuel.add(new Question("What is the largest ocean on Earth?", "Pacific"));

        questionsDuel.add(new Question("What is the main organ of the human respiratory system?", "Lungs"));

        questionsDuel.add(new Question("Who painted the Mona Lisa?", "Leonardo da Vinci"));

        questionsDuel.add(new Question("How many continents are there on Earth?", "7"));

        questionsDuel.add(new Question("What is the currency used in Japan?", "Yen"));

        questionsDuel.add(new Question("What is the most abundant chemical element in the universe?", "Hydrogen"));

        questionsDuel.add(new Question("What is the largest mammal?", "Blue whale"));

        questionsDuel.add(new Question("What is the boiling point of water in Celsius?", "100"));

        questionsDuel.add(new Question("Who wrote 'Romeo and Juliet'?", "William Shakespeare"));

        questionsDuel.add(new Question("What is the tallest mountain in the world?", "Mount Everest"));

        questionsDuel.add(new Question("What is the largest desert in the world?", "Sahara"));

        questionsDuel.add(new Question("Who discovered penicillin?", "Alexander Fleming"));

        questionsDuel.add(new Question("What is the chemical symbol for gold?", "Au"));

        questionsDuel.add(new Question("What is the smallest bone in the human body?", "Stapes"));

        questionsDuel.add(new Question("What is the capital of Australia?", "Canberra"));

        questionsDuel.add(new Question("Who was the first person to step on the moon?", "Neil Armstrong"));

        questionsDuel.add(new Question("What is the chemical symbol for water?", "H2O"));

        questionsDuel.add(new Question("What is the largest planet in our solar system?", "Jupiter"));

        questionsDuel.add(new Question("Who is known as the 'Father of Computers'?", "Charles Babbage"));

        questionsDuel.add(new Question("What is the world's longest river?", "Nile"));

        questionsDuel.add(new Question("What is the main ingredient in guacamole?", "Avocado"));

        questionsDuel.add(new Question("Who painted the ceiling of the Sistine Chapel?", "Michelangelo"));

        questionsDuel.add(new Question("What is the capital of Brazil?", "Brasília"));

        questionsDuel.add(new Question("What is the chemical symbol for iron?", "Fe"));

        questionsDuel.add(new Question("What is the hardest natural substance on Earth?", "Diamond"));

        questionsDuel.add(new Question("Who was the first female Prime Minister of the United Kingdom?", "Margaret Thatcher"));

        questionsDuel.add(new Question("What is the fastest land animal?", "Cheetah"));

        questionsDuel.add(new Question("What is the capital of China?", "Beijing"));

        questionsDuel.add(new Question("What is the study of earthquakes called?", "Seismology"));

        questionsDuel.add(new Question("Who wrote 'To Kill a Mockingbird'?", "Harper Lee"));

        questionsDuel.add(new Question("What is the chemical symbol for silver?", "Ag"));

        questionsDuel.add(new Question("What is the national animal of Australia?", "Kangaroo"));

        questionsDuel.add(new Question("Who composed 'Für Elise'?", "Ludwig van Beethoven"));

        questionsDuel.add(new Question("What is the largest bird in the world?", "Ostrich"));

        questionsDuel.add(new Question("What is the smallest country in the world?", "Vatican City"));

        questionsDuel.add(new Question("Who founded Microsoft?", "Bill Gates"));

        questionsDuel.add(new Question("What is the main ingredient in chocolate?", "Cocoa beans"));

        questionsDuel.add(new Question("What is the chemical symbol for sodium?", "Na"));

        questionsDuel.add(new Question("What is the hottest continent on Earth?", "Africa"));

        questionsDuel.add(new Question("Who wrote 'The Great Gatsby'?", "F. Scott Fitzgerald"));

        questionsDuel.add(new Question("What is the study of living organisms called?", "Biology"));

        questionsDuel.add(new Question("What is the capital of Russia?", "Moscow"));

        questionsDuel.add(new Question("What is the chemical symbol for helium?", "He"));

        questionsDuel.add(new Question("Who painted 'The Starry Night'?", "Vincent van Gogh"));

        questionsDuel.add(new Question("What is the largest moon of Jupiter?", "Ganymede"));

        questionsDuel.add(new Question("What is the study of celestial bodies called?", "Astronomy"));

        questionsDuel.add(new Question("Who discovered electricity?", "Benjamin Franklin"));

        questionsDuel.add(new Question("What is the chemical symbol for carbon?", "C"));

        questionsDuel.add(new Question("What is the largest artery in the human body?", "Aorta"));

        questionsDuel.add(new Question("What is the chemical symbol for oxygen?", "O"));

        questionsDuel.add(new Question("What is the national animal of Canada?", "Beaver"));

        questionsDuel.add(new Question("Who wrote '1984'?", "George Orwell"));

        questionsDuel.add(new Question("What is the chemical symbol for nitrogen?", "N"));

        questionsDuel.add(new Question("What is the deepest ocean trench?", "Mariana Trench"));

        questionsDuel.add(new Question("What is the largest land mammal?", "African elephant"));

        questionsDuel.add(new Question("What is the chemical symbol for potassium?", "K"));

        questionsDuel.add(new Question("Who discovered the theory of relativity?", "Albert Einstein"));
	}
	
    /**
     * Retrieves a random question from the deck
     *
     * @return A randomly selected question
     */
	public Question getRandomQuestion() {
		Random rand = new Random();
		int randInt = rand.nextInt(questionsDuel.size());
		
		return questionsDuel.get(randInt);
	}
}
