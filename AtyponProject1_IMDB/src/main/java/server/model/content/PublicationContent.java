package server.model.content;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class PublicationContent extends BaseContent implements Serializable, Comparable {
    private String title;
    private String body;

    public PublicationContent(int contentId, LocalDate timeStamp, int journalId, int authorId, String title, String body) {
        super(contentId, timeStamp, journalId, authorId);
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return getContentId();
    }

    @Override
    public int compareTo(Object o) {
        PublicationContent publicationContent = (PublicationContent) o;
        return Integer.compare(getContentId(), publicationContent.getContentId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PublicationContent that = (PublicationContent) o;
        return title.equals(that.title) &&
                body.equals(that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, body);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PublicationContent.class.getSimpleName() + "[", "]")
                .add("contentId=" + getContentId())
                .add("journalId=" + getJournalId())
                .add("authorId=" + getAuthorId())
                .add("timeStamp=" + getTimeStamp())
                .add("title='" + title + "'")
                .add("body='" + body + "'")
                .toString();
    }
}
