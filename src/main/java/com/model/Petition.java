package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Petition {
    private UUID id;
    private String title;
    private String description;
    private List<Signature> signatureList = new ArrayList<>();

    public Petition(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
    }

    public Petition() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addSignature(Signature signature) {
        signatureList.add(signature);
    }

    public List<Signature> getSignatureList() {
        return signatureList;
    }

}
