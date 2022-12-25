package teste.correios;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;

public class ConsumindoCorreios {

	public static void main(String[] args) throws MalformedURLException, SQLException_Exception, SigepClienteException {
		URL urlCorreios = new URL(
				"https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl");
		QName qnameCorreios = new QName("http://cliente.bean.master.sigep.bsb.correios.com.br/",
				"AtendeClienteService");
		Service serviceCorreios = Service.create(urlCorreios, qnameCorreios);

		// proxy para consumir o serviço
		AtendeCliente correios = serviceCorreios.getPort(AtendeCliente.class);

		EnderecoERP endereco = correios.consultaCEP("24370285");
		System.out.println("Endereço: " + endereco.getEnd());
		System.out.println("Bairro: " + endereco.getBairro());
		System.out.println("Cidade: " + endereco.getCidade());
		System.out.println("Estado: " + endereco.getUf());

	}
}
