/*
package org.jana.dropwizard;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@NamedQueries({
        @NamedQuery(name = "com.javaeeeee.dwstart.core.Employee.findAll",
                query = "select e from Employee e"),
        @NamedQuery(name = "com.javaeeeee.dwstart.core.Employee.findByName",
                query = "select e from Employee e "
                        + "where e.firstName like :name "
                        + "or e.lastName like :name")
})
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "desc")
    private String desc;

    @Column(name = "date")
    private String date;


    public Task() {
    }

    public Task(int id, String desc, String date) {
        this.id = id;
        this.desc = desc;
        this.date = date;
    }

}
*/
