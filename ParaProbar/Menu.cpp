#include "Menu.h"
#include <iostream>
using namespace std;

void Menu::MenuPrincipal()
{
}

void Menu::iniciarSesion()
{
	string usuario, contrasna, empresa, departamento;
	int opcion=0;
	bool acceso= true;
	do {
		cout << "Opción 1: Iniciar Sesión" << endl;
		cout << "Opción 2: Salir" << endl;
		cin >> opcion;
		if (opcion==1) {
			cout << "%%%%%%%%% RENTA ACTIVOS %%%%%%%%%" << endl;
			cout << "-------------Login---------------" << endl;
			cout << "Ingresar Usuario" << endl;
			cin >> usuario;
			cout << "Ingresar Contraseña" << endl;
			cin >> contrasna;
			cout << "Ingresar Empresa" << endl;
			cin >> empresa;
			cout << "Ingresar Departamento" << endl;
			cin >> departamento;

			acceso=logicaLogin(usuario, contrasna, empresa, departamento);

			if (acceso == false) {
				iniciarSesion();
			}
		}
		
	} while (opcion !=2);
	
}

void Menu::iniciarSesionAdmin()
{
	string Empresa, Departamento;
	Nodo* Empr;
	Nodo* Depa;
	int opcion;
	do {

		cout << "----------Menú Administrador----------" << endl;
		cout << "Opción 1: Registrar Usuario" << endl;
		cout << "Opción 2: Reporte Matriz Dispersa" << endl;
		cout << "Opción 3: Reporte Activos Disponibles de un Departamento" << endl;
		cout << "Opción 4: Reporte Activos Disponibles de una Empresa" << endl;
		cout << "Opción 5: Reporte Transacciones" << endl;
		cout << "Opción 6: Reporte Activos de un Usuario" << endl;
		cout << "Opción 7: Activos Rentados por un Usuario" << endl;
		cout << "Opción 8: Ordenar Trnasacciones" << endl;
		cout << "Opción 17: Cargar usuarios prueba" << endl;
		cout << "Opción 9: Cerrar Sesion" << endl;
		cin >> opcion;

		switch (opcion)
		{
		case 1:
			matrix->RegistroUsuario();
			break;
		case 2:
			matrix->graficar();
			break;
		case 3:
			cout << "%%%%%%%%% Activos Disponibles de un Departamento %%%%%%%%%" << endl;
			cout << "Ingresar Departamento" << endl;
			cin >> Departamento;
			try {
				Depa = matrix->buscarDepa(Departamento, matrix->Cabecera)->Abajo;
				do {
					if (Depa->getAVL() != nullptr) {
						Depa->getAVL()->graficar();
					}
					Depa = Depa->Abajo;
				} while (Depa != nullptr);
			}
			catch (exception) { cout << "No se encontró activos registrados en ese departamento" << endl; }
			
			break;
		case 4:
			cout << "%%%%%%%%% Activos Disponibles de una Empresa %%%%%%%%%" << endl;
			cout << "Ingresar Empresa" << endl;
			cin >> Empresa;
			try {
				Empr = matrix->buscarEmpresa(Empresa, matrix->Cabecera)->Siguiente;
				do {
					if (Empr->getAVL() != nullptr) {
						Empr->getAVL()->graficar();
					}
					Empr = Empr->Siguiente;
				} while (Empr != nullptr);
			}
			catch (exception) {
				cout << "No se encontró activos registrados en esa Empresa"<<endl;
			}
			
			break;
		case 5:
			LCD->graficar();
			break;
		case 6:
			ReporteActivoUsuario();
			break;
		case 17:
			//miObjeto->InsertarElemento(usuario, numero,empresa,departamento);
			matrix->InsertarElemento("mynor", "usuario1", "contrasena1", 1, "max", "Guatemala");
			matrix->InsertarElemento("susan", "usuario1", "contrasena1", 2, "hp", "jutiapa");
			matrix->InsertarElemento("susel", "usuario1", "contrasena1", 3, "hp", "jalapa");
			matrix->InsertarElemento("roxana", "usuario1", "contrasena1", 4, "walmart", "jalapa");
			matrix->InsertarElemento("andrea", "usuario1", "contrasena1", 5, "walmart", "jalapa");
			matrix->InsertarElemento("sebas", "usuario1", "contrasena1", 6, "walmart", "jalapa");
			matrix->InsertarElemento("andres", "usuario1", "contrasena1", 7, "hp", "Guatemala");
			matrix->InsertarElemento("willy", "usuario1", "contrasena1", 8, "max", "jalapa");
			matrix->InsertarElemento("mynor2", "usuario1", "contrasena1", 8, "walmart", "Guatemala");
			matrix->InsertarElemento("AUDRIE", "usuario1", "contrasena1", 8, "hp", "Guatemala");
			break;
		}
	} while (opcion != 9);
	system("cls");
	iniciarSesion();



}

bool Menu::logicaLogin(string usuario, string contrasena, string empresa, string departamento)
{
	Nodo* Usuario = matrix->BuscarUsuario(usuario, contrasena, empresa, departamento);
	if (Usuario != nullptr) {
		if (Usuario->Usuario == "Administrador" && Usuario->Contrasena == "201801263") {
			iniciarSesionAdmin();
			return true;
		}
		else {
			matrix->setUsuario(Usuario);
			matrix->MenuUsuario();
			iniciarSesion();
			return true;
		}
	}
	else {
		cout << "Usuario no registrado, verifique sus datos";
		return false;
	}
}

void Menu::ReporteActivoUsuario()
{
	string usuario, contrasna, empresa, departamento;
	Nodo* Usuario;

	cout << "%%%%%%%%% REPORTE ACTIVOS DE UN USUARIO %%%%%%%%%" << endl;
	cout << "Ingresar Usuario" << endl;
	cin >> usuario;
	cout << "Ingresar Contraseña" << endl;
	cin >> contrasna;
	cout << "Ingresar Empresa" << endl;
	cin >> empresa;
	cout << "Ingresar Departamento" << endl;
	cin >> departamento;
	Usuario = matrix->BuscarUsuario(usuario, contrasna, empresa, departamento);	
	Arbol_AVL* ArbolUsuario= Usuario->getAVL();
	ArbolUsuario->graficar();
}


