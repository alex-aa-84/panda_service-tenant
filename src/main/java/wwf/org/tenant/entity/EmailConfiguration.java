package wwf.org.tenant.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="tn_emails_configuration", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email_host", "email_username", "email_port", "email_from"})
})
@Data
public class EmailConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @NotEmpty(message = "The email_host can not be empty")
    @Column(nullable = false)
    private String email_host;

    @NotEmpty(message = "The email_username can not be empty")
    @Column(nullable = false)
    private String email_username;

    @NotEmpty(message = "The email_password can not be empty")
    @Column(nullable = false)
    private String email_password;

    @NotEmpty(message = "The email_port can not be empty")
    @Column(nullable = false)
    private String email_port;

    @NotEmpty(message = "The email_from can not be empty")
    @Column(nullable = false)
    private String email_from;

    @NotEmpty(message = "The email_from_name can not be empty")
    @Column(nullable = false)
    private String email_from_name;

    private Integer attribute1;
    private Integer attribute2;
    private Integer attribute3;
    private Integer attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;

    @Temporal(TemporalType.DATE)
    private Date attribute9 ;

    @Temporal(TemporalType.TIMESTAMP)
    private Date attribute10;

    private String status;
    private Long create_by;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation_date;

    private Long last_update_by;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_update_date;
}
