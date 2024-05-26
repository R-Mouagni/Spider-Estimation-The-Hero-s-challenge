package model;

import java.util.ArrayList;
import java.util.Objects;

import exception.QuestionAlreadyExistsException;

/**
 * Represents a basic card containing information about the author, theme, subject, and a list of questions
 */
public class BasicCard {
    private String author;
    private Theme theme;
    private String subject;
    private ArrayList<Question> questions;

    /**
     * Constructs a new BasicCard object with the specified author, theme, and subject
     *
     * @param author  The author of the card
     * @param theme   The theme of the card
     * @param subject The subject of the card
     */
    public BasicCard(String author, Theme theme, String subject) {
        super();
        this.author = author;
        this.theme = theme;
        this.subject = subject;
        this.questions = new ArrayList<>();
    }

    /**
     * Adds a question to the card's list of questions
     *
     * @param q The question to be added
     * @throws QuestionAlreadyExistsException If the question already exists in the card's list of questions
     */
    public void add(Question q) throws QuestionAlreadyExistsException {
        if (!questions.contains(q)) {
            questions.add(q.clone());
        } else {
            throw new QuestionAlreadyExistsException();
        }
    }

    /**
     * Retrieves the list of questions associated with the card
     *
     * @return The list of questions
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Retrieves the theme of the card
     *
     * @return The theme of the card
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Retrieves the subject of the card
     *
     * @return The subject of the card
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the list of questions associated with the card
     *
     * @param questions The list of questions to be set
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * Retrieves the author of the card
     *
     * @return The author of the card
     */
    public String getAuthor() {
        return author;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, theme);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BasicCard other = (BasicCard) obj;
        return Objects.equals(subject, other.subject) && theme == other.theme;
    }

    /**
     * Creates and returns a clone of the BasicCard object
     *
     * @return A clone of the BasicCard object
     */
    public BasicCard clone() {
        return new BasicCard(author, theme, subject);
    }
}
