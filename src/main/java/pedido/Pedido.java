package pedido;

import ingredientes.Adicional;
import produto.Shake;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens, Cliente cliente) {
        this.id = id;
        this.itens = itens;
        this.cliente = cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    //Refactor -> use a Map/Stream
    public double calcularTotal(Cardapio cardapio) {
        double total = 0;
        for (ItemPedido item : itens) {
            Shake shake = item.getShake();

            double base = cardapio.buscarPreco(shake.getBase());
            double tamanho = shake.getTipoTamanho().multiplicador;
            double adicionais = 0;

            if (shake.getAdicionais() != null) {
                List<Adicional> listaAdicionais = shake.getAdicionais();
                for (Adicional adicional : listaAdicionais) {
                    adicionais += cardapio.buscarPreco(adicional);
                }
            }

            double valor = (base * tamanho) + adicionais;
            double quantidade = item.getQuantidade();
            total += valor * quantidade;
        }
        return total;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado) {

        for (ItemPedido item : itens) {
            if (item.getShake().equals(itemPedidoAdicionado.getShake())) {
                item.setQuantidade(item.getQuantidade() + itemPedidoAdicionado.getQuantidade());
            } else {
                itens.add(itemPedidoAdicionado);
                break;
            }
        }
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        int quantidade = itemPedidoRemovido.getQuantidade();

        if (itens.contains(itemPedidoRemovido)) {
            itemPedidoRemovido.setQuantidade(--quantidade);
            if (quantidade < 0) {
                itens.remove(itemPedidoRemovido);
            }
        } else {
            throw new IllegalArgumentException("Item nao existe no pedido.");
        }
        return false;
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
