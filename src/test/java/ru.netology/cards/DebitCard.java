package ru.netology.cards;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DebitCard {

    private String amount;
    private String status;

}
