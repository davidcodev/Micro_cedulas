package com.cedulas.services;

import com.cedulas.entities.Cedula;
import com.cedulas.models.ReqGenera;
import com.cedulas.models.Request;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Random;

@ApplicationScoped
public class CedulaService {
    Random random = new Random();

    /**
     * Lista todas las cedulas de la base
     * @return List
     */
    public List<Cedula> listar() {
        return Cedula.listAll();
    }

    /**
     * Graba en la base la cédula que se envíe
     * @param req JSON de entrada
     * @return mensaje OK
     */
    @Transactional
    public String grabar(Request req) {
        if(Cedula.findById(req.getCedula()) == null){
            Cedula cedula = new Cedula();
            cedula.setCedula(req.getCedula());
            cedula.persist();
            return ("OK");
        }else return ("NO SE GRABÓ, YA EXISTE EN LA BASE");
    }

    /**
     * Genera cédulas en orden: 0100000001,... hasta la cantidad que se especifique
     * @param req JSON de entrada
     * @return
     */
    @Transactional
    public String genera(ReqGenera req) {
        int provincia = req.getProvincia();
        int cantidad = req.getCantidad();
        String prov = provincia < 10 ? "0" + provincia : String.valueOf(provincia);

        int n = 0;
        int v = -1;
        StringBuilder c;
        for (int k = 0; k < cantidad; k++) {
            do {
                c = new StringBuilder();
                v++;
                c.append(prov).append(String.format("%07d", n)).append(v);
            } while (!esValida(c.toString()));
            Cedula cedula = new Cedula();
            cedula.setCedula(c.toString());
            cedula.persist();
            n++;
            v = -1;
        }
        return "OK";
    }

    /**
     * Genera cédulas aleatorias (no verifica si ya existe en la base por lo que podría dar error de llave duplicada)
     * @param req JSON de entrada
     * @return
     */
    @Transactional
    public String generaA(ReqGenera req) {
        int provincia = req.getProvincia();
        int cantidad = req.getCantidad();
        String prov = provincia < 10 ? "0" + provincia : String.valueOf(provincia);

        StringBuilder c;
        int verif;
        for (int k = 0; k < cantidad; k++) {
            c = new StringBuilder();
            c.append(prov).append(String.format("%07d", random.nextInt(10000000) + 1));
            verif = verificador(c.toString());
            c.append(verif);
            Cedula cedula = new Cedula();
            cedula.setCedula(c.toString());
            cedula.persist();
        }
        return "OK";
    }

    /**
     * Elimina todas las cedulas de la base
     */
    @Transactional
    public void elimina() {
        Cedula.deleteAll();
    }

    public String valida(Request req) {
        return esValida(req.getCedula()) ? "VALIDA" : "NO VALIDA";
    }

    /**
     * Valida un número de cédula
     * @param id cédula a validar
     * @return true / false
     */
    public boolean esValida(String id) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int num = id.charAt(i) - '0';
            sum += (i % 2 == 0) ? (num * 2 > 9 ? num * 2 - 9 : num * 2) : num;
        }
        return sum % 10 == 0;
    }

    /**
     * Devuelve el dígito verificador de una cedula
     * @param ced cédula a validar
     * @return int - Dígito verificador
     */
    public int verificador(String ced) {
        StringBuilder c;
        int n = -1;
        do{
            c = new StringBuilder();
            n++;
            c.append(ced, 0, 9).append(n);
        }while (!esValida(c.toString()));
        return n;
    }
}
