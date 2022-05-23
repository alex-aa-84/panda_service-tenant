package wwf.org.tenant.entity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="tn_databases", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"db_connection", "db_host", "db_port", "db_database"})
})
@Data
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "The db_connnection can not be empty")
    @Column(nullable = false)
    private String db_connection;

    @NotEmpty(message = "The db_host can not be empty")
    @Column(nullable = false)
    private String db_host;

    @NotEmpty(message = "The db_port can not be empty")
    @Column(nullable = false)
    private String db_port;

    @NotEmpty(message = "The db_database can not be empty")
    @Column(nullable = false)
    private String db_database;

    @NotEmpty(message = "The db_username can not be empty")
    @Column(nullable = false)
    private String db_username;

    @NotEmpty(message = "The db_password can not be empty")
    @Column(nullable = false)
    private String db_password;

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
