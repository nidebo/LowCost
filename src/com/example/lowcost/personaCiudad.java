package com.example.lowcost;

public class personaCiudad {
		
		private String Nombre="";
		private String Ciudad="";
		//coordenadas?
		//id?

		public String getNombre(){
			return Nombre;
		}
		public String getCiudad(){
			return Ciudad;
		}
		public void setNombre(String nombre) {
	        	Nombre = nombre;
	    	}
		public void setCiudad(String ciudad) {
			Ciudad = ciudad;
		}
}
