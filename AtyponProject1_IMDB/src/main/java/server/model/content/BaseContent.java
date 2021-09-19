package server.model.content;

import server.model.Identifiable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class BaseContent implements Comparable, Serializable, Identifiable {
    private int contentId;
    private LocalDate timeStamp;
    private int journalId;
    private int authorId;

    public BaseContent(int contentId, LocalDate timeStamp, int journalId, int authorId) {
        this.contentId = contentId;
        this.timeStamp = timeStamp;
        this.journalId = journalId;
        this.authorId = authorId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getJournalId() {
        return journalId;
    }

    public void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public int getContentId() {
        return contentId;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseContent)) return false;
        BaseContent that = (BaseContent) o;
        return contentId == that.contentId &&
                journalId == that.journalId &&
                authorId == that.authorId &&
                timeStamp.equals(that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, timeStamp, journalId, authorId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseContent{");
        sb.append("contentId=").append(contentId);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", journalId=").append(journalId);
        sb.append(", authorId=").append(authorId);
        sb.append('}');
        return sb.toString();
    }
}
