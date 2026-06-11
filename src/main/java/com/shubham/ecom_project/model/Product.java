package com.shubham.ecom_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product") // Saaf saaf bata diya ki Postgres ki 'product' table hai
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // React se 'desc' aayega, par Postgres DB mein column 'description' hai
    @Column(name = "description")
    @JsonProperty("desc")
    private String desc;

    private String brand;
    private BigDecimal price;
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "release_date") // Postgres mein release_date hai
    private Date releaseDate;

    // React se 'available' aayega, DB mein bhi 'available' hai
    @JsonProperty("available")
    private boolean available;

    // React se 'stockQuantity' aayega, par DB mein column 'quantity' hai
    @Column(name = "quantity")
    @JsonProperty("stockQuantity")
    private Integer quantity;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;


    @Column(name = "image_data", columnDefinition = "bytea")
    private byte[] imageData;
}