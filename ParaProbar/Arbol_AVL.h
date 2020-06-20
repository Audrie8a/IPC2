#pragma once
#include <iostream>;
#include "NodoArbol.h"
using namespace std;
class Arbol_AVL
{
public:
	NodoArbol* Raiz;
	NodoArbol* Actual;

	int cont;	//contador
	int altura;

public:
	Arbol_AVL() {
		Raiz = nullptr;
		Actual = nullptr;
	}

	
	//--------------------------------------------------------------------------------------------------------------------CREAR M�TODOS AUXILIARES
	NodoArbol* BuscarNodoAVL(string dato, NodoArbol* Raiz);
	int obtenerFe(NodoArbol* Nodo);
	void ActualizarAltura(NodoArbol* Nodo);
	void MostrarArbol(NodoArbol* arbol, int cont);
	void CambiarEstadoNodo(string dato,NodoArbol* Raiz, int numero);	//Cambia el estado del nodo rentado a false
	

	//--------------------------------------------------------------------------------------------------------------------CREAR M�TODOS PARA ROTACIONES

	NodoArbol* SRL(NodoArbol* Nodo);		//ROTACI�N SIMPLE A LA IZQUIERDA
	NodoArbol* SRR(NodoArbol* Nodo);		//ROTACI�N SIMPLE A LA DERECHA
	NodoArbol* DRR(NodoArbol* Nodo);		//ROTACI�N DOBLE A LA DERECHA
	NodoArbol* DRL(NodoArbol* Nodo);		//ROTACI�N DEOBLE A LA IZQUIERDA

	//---------------------------------------------------------------------------------------------------------------------CREAR M�TODOS PARA FUNCIONES PRINCIPALES

	NodoArbol* InsertarAVl(NodoArbol* Nuevo, NodoArbol* Nodo);
	void InsertarNodoAVL(string Nombre, string Descripcion, bool estado);


	//----------------------------------------------------------------------------------------------------------------------GRAFICAR
	void graficar();
	void getDot(FILE* file, NodoArbol* Raiz);
	string CuerpoDot(NodoArbol* Raiz);


	//-----------------------------------------------------------------------------------------------------------------------M�TODOS MEN�
	void MostrarActivosArbol(NodoArbol* arbol);
	void MisActivos(NodoArbol* arbol); //Activos que yo Rent�
	//-----------------------------------------------------------------------------------------------------------------------get y sets
	NodoArbol* getRaiz();
	int getContador() {
		return this->cont;
	};

	void setContador(int contador) {
		this->cont = contador;
	}

};