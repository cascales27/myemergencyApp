package com.emergencias.utils;

import com.emergencias.model.HealthCenter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class HealthCenterFinder {

    public static List<HealthCenter> findByMunicipio(List<HealthCenter> centros, String municipioInput) {
        List<HealthCenter> res = new ArrayList<>();
        if (centros == null || municipioInput == null) return res;

        String target = norm(municipioInput);

        for (HealthCenter c : centros) {
            if (c == null) continue;

            String muOriginal = c.getMunicipio();
            String mu = norm(muOriginal);

            // ✅ 1) Match exacto
            if (mu.equals(target)) {
                res.add(c);
                continue;
            }

            // ✅ 2) Match parcial (ej: "alicante" dentro de "alicante/alacant")
            if (mu.contains(target)) {
                res.add(c);
                continue;
            }

            // ✅ 3) Si tiene barra, dividir y comparar cada parte
            if (mu.contains("/")) {
                String[] partes = mu.split("/");
                for (String p : partes) {
                    if (p.trim().equals(target) || p.trim().contains(target)) {
                        res.add(c);
                        break;
                    }
                }
            }
        }

        return res;
    }

    private static String norm(String s) {
        if (s == null) return "";
        String t = s.trim().toLowerCase();
        t = Normalizer.normalize(t, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        t = t.replaceAll("\\s+", " ");
        return t;
    }
}