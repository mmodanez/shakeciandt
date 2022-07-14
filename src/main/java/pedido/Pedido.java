package pedido;

import ingredientes.Ingrediente;

import java.util.ArrayList;

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

    //Fix -> multiplicador de tamanho
    public double calcularTotal(Cardapio cardapio) {
        double total = 0;
        for (ItemPedido item : itens) {
            double base = cardapio.buscarPreco(item.getShake().getBase());
            double fruta = cardapio.buscarPreco(item.getShake().getFruta());
            double topping = cardapio.buscarPreco(item.getShake().getTopping());
            total += base + fruta + topping;
        }
        return total;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado) {

        for (ItemPedido item: itens) {
            if (item.getShake().equals(itemPedidoAdicionado.getShake())) {
                item.setQuantidade(item.getQuantidade()+itemPedidoAdicionado.getQuantidade());
            } else {
                itens.add(itemPedidoAdicionado);
                break;
            }
        }
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        if (itens.contains(itemPedidoRemovido)) {
            itens.remove(itemPedidoRemovido);
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
