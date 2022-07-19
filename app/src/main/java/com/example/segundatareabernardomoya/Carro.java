package com.example.segundatareabernardomoya;

public class Carro {


    //private variables
    int _id;
    String _identificador;
    String _marca;
    String _nombre;
    String _modelo;
    String _numero_cilindros;
    String _precio;
    byte[] _img;

    public Carro(){

    }

    public Carro(String _identificador, String _marca, String _nombre, String _modelo, String _numero_cilindros, String _precio, byte[] _img) {
        this._identificador = _identificador;
        this._marca = _marca;
        this._nombre = _nombre;
        this._modelo = _modelo;
        this._numero_cilindros = _numero_cilindros;
        this._precio = _precio;
        this._img = _img;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_identificador() {
        return _identificador;
    }

    public void set_identificador(String _identificador) {
        this._identificador = _identificador;
    }

    public String get_marca() {
        return _marca;
    }

    public void set_marca(String _marca) {
        this._marca = _marca;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_modelo() {
        return _modelo;
    }

    public void set_modelo(String _modelo) {
        this._modelo = _modelo;
    }

    public String get_numero_cilindros() {
        return _numero_cilindros;
    }

    public void set_numero_cilindros(String _numero_cilindros) {
        this._numero_cilindros = _numero_cilindros;
    }

    public String get_precio() {
        return _precio;
    }

    public void set_precio(String _precio) {
        this._precio = _precio;
    }

    public byte[] get_img() {
        return _img;
    }

    public void set_img(byte[] _img) {
        this._img = _img;
    }

}
