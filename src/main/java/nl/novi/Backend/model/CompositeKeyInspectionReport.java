package nl.novi.Backend.model;

import javax.persistence.Column;
import java.io.Serializable;

public class CompositeKeyInspectionReport implements Serializable {

    private Long inspectionNumber;
    private Long executedItem;
}
