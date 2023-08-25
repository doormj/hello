package co.yeadam.hello;

import co.yeadam.hello.menu.ProductManager;

public class App 
{
    public static void main( String[] args )
    {
    	ProductManager menu = new ProductManager();
    	menu.run();
    }
}






//ProductService dao = new ProductServiceImpl();
//List<ProductVO> products = new ArrayList<ProductVO>();
//
//products = dao.productSelectList();
//
//for(ProductVO v : products) {
//	v.toString();
//}