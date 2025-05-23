/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.eps.apexeps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eps.apexeps.models.Ips;
import com.eps.apexeps.repositories.IpsRepository;

/**
 *
 * @author Alexander
 */
@Service
public class IpsService {

    @Autowired
    private IpsRepository ipsRepository;

    public List<Ips> findAll() {
        return ipsRepository.findAll();
    }

    /**
     * Filtra las IPS según los parámetros proporcionados. Los parámetros son
     * opcionales y se aplican en orden de prioridad.
     *
     * @param nombre         Nombre de la IPS (opcional)
     * @param telefono       Teléfono de la IPS (opcional)
     * @param direccion      Dirección de la IPS (opcional)
     * @param fechaRegistro  Fecha de registro de la IPS (opcional)
     * @return Lista de IPS que cumplen con los criterios de búsqueda
     */
    public List<Ips> filtrarIpsMulticriterio(String nombre, String telefono, String direccion, String fechaRegistro, String nombreServicio) {
        try {
            // Limpieza de datos
            nombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre.trim() : null;
            telefono = (telefono != null && !telefono.trim().isEmpty()) ? telefono.trim() : null;
            direccion = (direccion != null && !direccion.trim().isEmpty()) ? direccion.trim() : null;
            System.out.println(">> service FILTRO nombre: " + nombre);
            List<Ips> resultado = ipsRepository.filtrarIpsMultiples(nombre, telefono, direccion, fechaRegistro, nombreServicio);
            //List<Ips> resultado = ipsRepository.filtrarIpsMultiples(nombre, telefono, direccion);
            return resultado;

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al filtrar las IPS: " + e.getMessage(), e);
        }
    }


    public Optional<Ips> findById(Integer id) {
        return ipsRepository.findById(id);
    }

    public Ips save(Ips ips) {
        return ipsRepository.save(ips);
    }

    public void deleteById(Integer id) {
        ipsRepository.deleteById(id);
    }

    public Ips actualizarIps(Ips ips) {
        return ipsRepository.save(ips);
    }

    /**
     * Busca las IPS que ofrecen un servicio médico específico.
     *
     * @param nombreServicio Nombre del servicio médico
     * @return Lista de IPS que ofrecen el servicio médico
     */
    public List<Ips> obtenerIpsPorServicio(String nombreServicio) {
        System.out.println("Nombre del servicio: " + nombreServicio);
        return ipsRepository.buscarIpsPorNombreServicio(nombreServicio);
    }

    /**
     * Busca los servicios médicos ofrecidos por una IPS específica.
     *
     * @param nombreIps Nombre de la IPS
     * @param idIps    ID de la IPS
     * @return Lista de servicios médicos ofrecidos por la IPS
     */
    public List<String> obtenerServiciosPorNombreOIdIps(String nombreIps, Integer idIps) {
        return ipsRepository.buscarServicioPorNombreOIdIps(nombreIps, idIps);
    }
}
