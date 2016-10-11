package ee.dbg4f.iot.hub.gae;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

/**
 * @author bogdel on 10/11/16.
 */
@Entity
public class TelemetryEntry {

    @Id
    public Long id;

    @Index
    public Date date;

    private String reporter;

    private String authorizedUser;

    private String format;
    private String textBody;

    public TelemetryEntry() {
    }

    public TelemetryEntry(String reporter, String authorizedUser, String format, String textBody) {
        this.reporter = reporter;
        this.authorizedUser = authorizedUser;
        this.format = format;
        this.textBody = textBody;
        date = new Date();
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getReporter() {
        return reporter;
    }

    public String getAuthorizedUser() {
        return authorizedUser;
    }

    public String getFormat() {
        return format;
    }

    public String getTextBody() {
        return textBody;
    }

    @Override
    public String toString() {
        return "TelemetryEntry{" +
                "id=" + id +
                ", date=" + date +
                ", reporter='" + reporter + '\'' +
                ", authorizedUser='" + authorizedUser + '\'' +
                ", format='" + format + '\'' +
                ", textBody='" + textBody + '\'' +
                '}';
    }
}
