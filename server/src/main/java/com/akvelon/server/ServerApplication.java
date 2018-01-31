package com.akvelon.server;

import com.akvelon.server.dao.impl.ProductDescriptionDaoImpl;
import com.akvelon.server.dao.impl.ProductModelDaoImpl;
import com.akvelon.server.service.api.ProductCategoryService;
import com.akvelon.server.service.impl.ProductCategoryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

		// =================== ProductCategoryDaoImpl testing ===========================
		/*DaoFactory.getInstance().getProductCategoryDao().getAll().forEach(System.out::println);
		System.out.println(DaoFactory.getInstance().getProductCategoryDao().read(2));
		System.out.println(DaoFactory.getInstance().getProductCategoryDao().readBy("Name", "Clothing"));

		ProductCategory productCategory = new ProductCategory();
		ProductCategory productCategory1 = new ProductCategory();
		productCategory.setName("Bikes-shmajkes");
		productCategory1.setName("Bikes-mamajkes");
		DaoFactory.getInstance().getProductCategoryDao().create(productCategory);
		DaoFactory.getInstance().getProductCategoryDao().create(productCategory1);

		System.out.println(productCategory1.getId() + " " + productCategory1);

		productCategory1.setName("Trololo");
		DaoFactory.getInstance().getProductCategoryDao().update(productCategory1);

		DaoFactory.getInstance().getProductCategoryDao().delete(productCategory.getId());*/


		// =================== CultureDaoImpl testing ===========================
		/*DaoFactory.getInstance().getCultureDao().getAll().forEach(System.out::println);
		System.out.println(DaoFactory.getInstance().getCultureDao().read("es"));
		System.out.println(DaoFactory.getInstance().getCultureDao().readBy("Name", "French"));

		Culture culture = new Culture();
		Culture culture1 = new Culture();
		culture.setId("ru");
		culture.setName("Russian");
		culture1.setId("ua");
		culture1.setName("Ukraine");

		DaoFactory.getInstance().getCultureDao().create(culture);
		DaoFactory.getInstance().getCultureDao().create(culture1);

		System.out.println(culture1.getId() + " " + culture1);

		culture1.setName("Ukrainian");
		DaoFactory.getInstance().getCultureDao().update(culture1);

		DaoFactory.getInstance().getCultureDao().delete(culture.getId());*/


		// =================== ProductPhotoDaoImpl testing ===========================
		/*DaoFactory.getInstance().getProductPhotoDao().getAll().forEach(System.out::println);
		System.out.println(DaoFactory.getInstance().getProductPhotoDao().read(1));
		System.out.println(DaoFactory.getInstance().getProductPhotoDao().readBy("ThumbnailPhotoFileName", "racer02_black_f_small.gif"));*/


		// =================== IllustrationDaoImpl testing ===========================
		/*DaoFactory.getInstance().getIllustrationDao().getAll().forEach(System.out::println);
		System.out.println(DaoFactory.getInstance().getIllustrationDao().read(3));
		System.out.println(DaoFactory.getInstance().getIllustrationDao().readBy("IllustrationID", 3));

		Illustration illustration = new Illustration();
		Illustration illustration1 = new Illustration();
		illustration.setDiagram("Diagram1");
		illustration1.setDiagram("Diagram2");

		DaoFactory.getInstance().getIllustrationDao().create(illustration);
		DaoFactory.getInstance().getIllustrationDao().create(illustration1);

		System.out.println(illustration1.getId() + " " + illustration1);

		illustration1.setDiagram("Diagram2-1");
		DaoFactory.getInstance().getIllustrationDao().update(illustration1);

		DaoFactory.getInstance().getIllustrationDao().delete(illustration.getId());*/


		// =================== ProductSubcategoryDaoImpl testing ===========================
		/*DaoFactory.getInstance().getProductSubcategoryDao().getAll().forEach(System.out::println);
		System.out.println(DaoFactory.getInstance().getProductSubcategoryDao().read(10));
		System.out.println(DaoFactory.getInstance().getProductSubcategoryDao().readBy("Name", "Road Bikes"));

		ProductSubcategory productSubcategory = new ProductSubcategory();
		ProductSubcategory productSubcategory1 = new ProductSubcategory();
		productSubcategory.setName("Home Bikes");
		productSubcategory1.setName("New Bikes");
		productSubcategory.setCategory(DaoFactory.getInstance().getProductCategoryDao().read(3));
		productSubcategory1.setCategory(DaoFactory.getInstance().getProductCategoryDao().read(3));

		DaoFactory.getInstance().getProductSubcategoryDao().create(productSubcategory);
		DaoFactory.getInstance().getProductSubcategoryDao().create(productSubcategory1);

		System.out.println(productSubcategory1.getId() + " " + productSubcategory);

		productSubcategory1.setCategory(DaoFactory.getInstance().getProductCategoryDao().read(4));
		DaoFactory.getInstance().getProductSubcategoryDao().update(productSubcategory1);

		DaoFactory.getInstance().getProductSubcategoryDao().delete(productSubcategory.getId());*/


		// =================== ProductDescriptionDaoImpl testing ===========================
		/*ProductDescriptionDaoImpl.getInstance().getAll().forEach(System.out::println);
		System.out.println(ProductDescriptionDaoImpl.getInstance().read(64));
		System.out.println(ProductDescriptionDaoImpl.getInstance().readBy("rowguid", "301EED3A-1A82-4855-99CB-2AFE8290D641"));*/

		// =================== ProductModelDaoImpl testing ===========================
		//ProductModelDaoImpl.getInstance().getAll().forEach(System.out::println);


		// =================== ProductCategoryServiceImpl testing ===========================
		/*ProductCategoryService productCategoryService = ProductCategoryServiceImpl.getInstance();
		productCategoryService.getAll().forEach(System.out::println);*/



	}
}
