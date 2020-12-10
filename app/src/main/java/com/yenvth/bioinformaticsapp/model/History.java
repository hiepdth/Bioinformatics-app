package com.yenvth.bioinformaticsapp.model;

public class History {
    private int his_id;
    private String protein_id_1;
    private String protein_id_2;
    private String protein_name_1;
    private String protein_name_2;
    private int interaction;
    private double probability;

    public History() {
    }
    public History(String protein_id_1, String protein_id_2, String protein_name_1, String protein_name_2, int interaction, double probability) {
        this.protein_name_1 = protein_name_1;
        this.protein_name_2 = protein_name_2;
        this.protein_id_1 = protein_id_1;
        this.protein_id_2 = protein_id_2;
        this.interaction = interaction;
        this.probability = probability;
    }

    public History(int his_id, String protein_id_1, String protein_id_2, String protein_name_1, String protein_name_2, int interaction, double probability) {
        this.his_id = his_id;
        this.protein_name_1 = protein_name_1;
        this.protein_name_2 = protein_name_2;
        this.protein_id_1 = protein_id_1;
        this.protein_id_2 = protein_id_2;
        this.interaction = interaction;
        this.probability = probability;
    }

    public int getHis_id() {
        return his_id;
    }

    public void setHis_id(int his_id) {
        this.his_id = his_id;
    }

    public String getProtein_name_1() {
        return protein_name_1;
    }

    public void setProtein_name_1(String protein_name_1) {
        this.protein_name_1 = protein_name_1;
    }

    public String getProtein_name_2() {
        return protein_name_2;
    }

    public void setProtein_name_2(String protein_name_2) {
        this.protein_name_2 = protein_name_2;
    }

    public String getProtein_id_1() {
        return protein_id_1;
    }

    public void setProtein_id_1(String protein_id_1) {
        this.protein_id_1 = protein_id_1;
    }

    public String getProtein_id_2() {
        return protein_id_2;
    }

    public void setProtein_id_2(String protein_id_2) {
        this.protein_id_2 = protein_id_2;
    }

    public int getInteraction() {
        return interaction;
    }

    public void setInteraction(int interaction) {
        this.interaction = interaction;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
