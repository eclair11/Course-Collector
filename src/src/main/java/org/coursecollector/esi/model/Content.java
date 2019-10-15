package org.coursecollector.esi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String format;
    String link;

    public Content(String format, String link) {
        this.format = format;
        this.link = link;
    }

    public Content() {

    }

}
