package com.thechief.engine.entity.tile.puzzle;

public class TypeChar {

	char c;
	
	public TypeChar(char c) {
		this.c = c;
	}
	
	public char getChar() {
		return c;
	}
	
	public char getShiftedVersion() {
		if (c == '1') {
			return '!';
		}
		if (c == '2') {
			return '@';
		}
		if (c == '3') {
			return '#';
		}
		if (c == '4') {
			return '$';
		}
		if (c == '5') {
			return '%';
		}
		if (c == '6') {
			return '^';
		}
		if (c == '7') {
			return '&';
		}
		if (c == '8') {
			return '*';
		}
		if (c == '9') {
			return '(';
		}
		if (c == '0') {
			return ')';
		}
		if (c == '-') {
			return '_';
		}
		if (c == '=') {
			return '+';
		}
		if (c == '[') {
			return '{';
		}
		if (c == ']') {
			return '}';
		}
		if (c == '\\') {
			return '|';
		}
		if (c == ';') {
			return ':';
		}
		if (c == '\'') {
			return '"';
		}
		if (c == ',') {
			return '<';
		}
		if (c == '.') {
			return '>';
		}
		if (c == '/') {
			return '?';
		}
		if (c == '`') {
			return '~';
		}
		return '!';
	}
	
}
