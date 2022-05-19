package wwf.org.tenant.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY ,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="tenant_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Tenant tenant_id;

    @Column(nullable = false)
    private String email_host;
    @Column(nullable = false)
    private String email_username;
    @Column(nullable = false)
    private String email_password;
    @Column(nullable = false)
    private String email_port;
    @Column(nullable = false)
    private String email_from;
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
