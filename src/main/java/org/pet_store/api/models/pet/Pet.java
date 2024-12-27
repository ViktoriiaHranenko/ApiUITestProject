package org.pet_store.api.models.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pet {
    private String id;
    private String name;
    private String status;

    public String getDescription() {
        return String.format("Pet with name '%s' and status '%s'", name, status);
    }
}
