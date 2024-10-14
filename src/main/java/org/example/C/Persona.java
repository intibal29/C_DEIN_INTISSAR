package org.example.C;

import java.util.Objects;

/**
 * La clase {@code Persona} representa una persona con un nombre, apellidos y edad.
 * Esta clase incluye métodos para acceder a las propiedades de la persona y
 * sobrescribe los métodos {@code equals} y {@code hashCode} para permitir la
 * comparación de objetos de tipo {@code Persona}.
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;

    /**
     * Crea una nueva instancia de {@code Persona} con el nombre, apellidos y edad especificados.
     *
     * @param nombre    el nombre de la persona
     * @param apellidos los apellidos de la persona
     * @param edad      la edad de la persona
     */
    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos de la persona.
     *
     * @return los apellidos de la persona
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene la edad de la persona.
     *
     * @return la edad de la persona
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Compara este objeto {@code Persona} con el objeto especificado para ver si son iguales.
     * Dos objetos son considerados iguales si tienen el mismo nombre, apellidos y edad.
     *
     * @param o el objeto a comparar con esta {@code Persona}
     * @return {@code true} si el objeto especificado es igual a esta {@code Persona},
     * {@code false} en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return edad == persona.edad &&
                Objects.equals(nombre, persona.nombre) &&
                Objects.equals(apellidos, persona.apellidos);
    }

    /**
     * Devuelve un valor de hash para esta {@code Persona}.
     *
     * @return un valor de hash para esta {@code Persona}
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos, edad);
    }
}
