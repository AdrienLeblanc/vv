package com.istic.m2.vv;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtField;
import spoon.reflect.reference.CtTypeReference;

import java.util.List;

public class Personne extends AbstractProcessor<CtField> {
	
	private String nom;
	
	private String prenom;
	
	private int age;
	
	private List<String> loisirs;
	
	public Personne(String nom, String prenom, int age, List<String> loisirs) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.loisirs = loisirs;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder(nom + " " + prenom + " | Age : " + age + "\n");
		if (!loisirs.isEmpty()) System.out.print("Loisirs: ");
		for (String loisir : loisirs) {
			str.append(loisir).append(" ");
		}
		return str.toString();
	}
	
	public void process(CtField ctField) {
		CtExpression expression = ctField.getAssignment();
		String nomAttribut = expression.toString();
		CtTypeReference type = ctField.getReference().getDeclaringType();
		String nomType = type.getSimpleName();
		String visibilite = type.getAccessType().toString();
		
		System.out.println(visibilite + " " + nomType + " " + nomAttribut);
	}
}
