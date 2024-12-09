package org.pet_store.api.models.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.common.models.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
public class Pet implements BaseEntity {
    private String id;
    private String name;
    private String status;

    public Pet() {}

    public Pet(String name, String status) {
    }
}
