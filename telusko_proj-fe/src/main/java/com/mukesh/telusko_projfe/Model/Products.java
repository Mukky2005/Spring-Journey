package com.mukesh.telusko_projfe.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private Integer price;
    private String category;
    private Date releaseDate;
    private boolean productAvailable;
    private Integer StockQuantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;


}
