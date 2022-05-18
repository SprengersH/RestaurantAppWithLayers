package Interfaces;

import DTO.ItemDTO;

import java.util.ArrayList;

public interface ItemRepository {

    ArrayList<ItemDTO> getItems();

    ArrayList<ItemDTO> selectMenu(int menunumber);
}
