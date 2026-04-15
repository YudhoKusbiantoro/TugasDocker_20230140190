package com.tugas.deploy6.model;

import lombok.Data;

@Data // Ini otomatis membuat Getter, Setter, dan Constructor dari Lombok
public class User {
    private String nama;
    private String nim;
    private String jenisKelamin;
}
