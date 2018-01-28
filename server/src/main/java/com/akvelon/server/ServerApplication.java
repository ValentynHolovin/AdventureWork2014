package com.akvelon.server;

import com.akvelon.server.dao.DaoFactory;
import com.akvelon.server.model.Culture;
import com.akvelon.server.model.ProductCategory;
import com.akvelon.server.model.ProductPhoto;
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


	}
}
