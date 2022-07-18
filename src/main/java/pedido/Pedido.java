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
        final var itemPedidoExistenteIndex = itens.indexOf(itemPedidoAdicionado);

        if (itemPedidoExistenteIndex == -1){
            itens.add(itemPedidoAdicionado);
        } else {
            final var itemPedidoExistente = itens.get(itemPedidoExistenteIndex);
            itemPedidoExistente.setQuantidade(itemPedidoExistente.getQuantidade() +
                    itemPedidoAdicionado.getQuantidade());
        }
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        final var itemPedidoExistenteIndex = itens.indexOf(itemPedidoRemovido);

        if (itemPedidoExistenteIndex == -1){
            throw new IllegalArgumentException("Item nao existe no pedido.");
        } else {
            final var itemPedidoExistente = itens.get(itemPedidoExistenteIndex);

            int quantidade = itemPedidoExistente.getQuantidade();

            if (itemPedidoExistente.getShake().equals(itemPedidoRemovido.getShake())) {
                itemPedidoExistente.setQuantidade(--quantidade);
            }
            if (quantidade == 0) {
                itens.remove(itemPedidoRemovido);
            }
        }
        return false;
}

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
