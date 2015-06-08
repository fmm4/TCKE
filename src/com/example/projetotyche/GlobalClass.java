package com.example.projetotyche;

import java.util.Vector;

import android.app.Application;
 
public class GlobalClass extends Application{
	private Vector listOfProducts;
	private int indexOfProduct = 0;
	
	public void start(){
		listOfProducts = new Vector();
	}
	
	public boolean isNull(){
		if(listOfProducts==null){
			return true;
		}else{
			return false;
		}
	}
	
	public int getSize(){
		return listOfProducts.size();
	}
	
	public void addProductToVector(String newProd){
		listOfProducts.add(newProd);
	}
	
	public String getCurrent(){
		return listOfProducts.get(indexOfProduct).toString();
	}
	
	public int currPos(){
		return indexOfProduct;
	}
	
	public void next(){
		if(indexOfProduct<listOfProducts.size()-1){
			indexOfProduct++;
		}else{
			indexOfProduct=0;
		}
	}
	
	public void back(){
		if(indexOfProduct>0){
			indexOfProduct--;
		}else{
			indexOfProduct=listOfProducts.size()-1;
		}
	}
	
	public void remProductFromVector(){
		listOfProducts.remove(indexOfProduct);
		if(indexOfProduct>=listOfProducts.size()){
			indexOfProduct = listOfProducts.size()-1;
		}
	}
}
 