package com.cjsanon.thrillio.entities;

import com.cjsanon.thrillio.constants.BookGenre;
import com.cjsanon.thrillio.partner.Shareable;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Book extends Bookmark implements Shareable {
    private int publicationYear;
    private String publisher;
    private String[] authors;
    private BookGenre genre;
    private double amazonRating;
    private String imageUrl;

    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getAuthors() {
        return authors;
    }
    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public BookGenre getGenre() {
        return genre;
    }
    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public double getAmazonRating() {
        return amazonRating;
    }
    public void setAmazonRating(double amazonRating) {
        this.amazonRating = amazonRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean isKidFriendlyEligible() {
        return !genre.equals(BookGenre.PHILOSOPHY) && !genre.equals(BookGenre.SELF_HELP);
    }

    @Override
    public String toString() {
        return "Book{" +
                "publicationYear=" + publicationYear +
                ", publisher='" + publisher + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", genre='" + genre + '\'' +
                ", amazonRating=" + amazonRating +
                '}';
    }

    @Override
    public String getItemData() {
        StringBuilder builder = new StringBuilder();
        builder.append("<item>");
            builder.append("<type>Book</type>");
            builder.append("<title>").append(getTitle()).append("</title>");
            builder.append("<authors>").append(StringUtils.join(authors, ",")).append("</authors>"); //single function that combines all authors into single string
            builder.append("<publisher>").append(publisher).append("</publisher>");
            builder.append("<publication>").append(genre).append("</publication>");
            builder.append("<amazonRating>").append(amazonRating).append("</amazonRating>");
        builder.append("</item>");

        return builder.toString();
    }
}
