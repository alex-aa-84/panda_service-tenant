package wwf.org.tenant.entity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="tn_databases", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dbConnection", "dbHost", "dbPort", "dbDatabase"})
})
@Data
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "db_connection_vacio")
    @Column(nullable = false)
    private String dbConnection;

    @NotEmpty(message = "db_host_vacio")
    @Column(nullable = false)
    private String dbHost;

    @NotEmpty(message = "db_port_vacio")
    @Column(nullable = false)
    private String dbPort;

    @NotEmpty(message = "db_database_vacio")
    @Column(nullable = false)
    private String dbDatabase;

    @NotEmpty(message = "db_username_vacio")
    @Column(nullable = false)
    private String dbUsername;

    @NotEmpty(message = "db_password_vacio")
    @Column(nullable = false)
    private String dbPassword;

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
