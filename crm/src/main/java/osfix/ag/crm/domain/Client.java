package osfix.ag.crm.domain;

import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="client")
    String client;

    @Column(name="contanct")
    String contact;

    @Column(name="address")
    String address;

    @Column(name="file")
    String file;

    @Column(name="status")
    String status;

    @Column(name="smpl")
    Boolean smpl;

}
