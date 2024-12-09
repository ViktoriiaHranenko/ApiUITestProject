package org.pet_store.api.models.pet;

public enum PetsAvailability {
    AVAILABLE,
    SOLD,
    PENDING;

    public String getStatus() {
        return this.name().toLowerCase();
    }
}
