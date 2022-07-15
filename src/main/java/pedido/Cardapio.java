package pedido;

import ingredientes.Ingrediente;

import java.util.Collections;
import java.util.TreeMap;

public class Cardapio {
    private TreeMap<Ingrediente, Double> precos;

    public Cardapio() {
        this.precos = new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos() {
        return this.precos;
    }

    public void adicionarIngrediente(Ingrediente ingrediente, Double preco) {
        if (preco <= 0.0) {
            throw new IllegalArgumentException("Preco Invalido");
        } else {
            precos.put(ingrediente, preco);
        }
    }

    //Refactor - IllegalArgumentException on Preco Invalido
    public boolean atualizarIngrediente(Ingrediente ingrediente, Double preco) {
        try {
            if (preco <= 0.0) {
                throw new IllegalArgumentException("Preco Invalido");
            } else {
                precos.replace(ingrediente, preco);
                return true;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Ingrediente não existe no cardápio");
        }
    }

    public boolean removerIngrediente(Ingrediente ingrediente) {
        try {
            precos.remove(ingrediente);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Ingrediente não existe no cardápio");
        }
    }

    public Double buscarPreco(Ingrediente ingrediente) {
        try {
            double preco = 0;

            for (int i = 0; i < precos.size(); i++) {
                preco = precos.get(ingrediente);
            }
            return preco;
        } catch (Exception e) {
            throw new IllegalArgumentException("Ingrediente não existe no cardápio");
        }
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }

}
