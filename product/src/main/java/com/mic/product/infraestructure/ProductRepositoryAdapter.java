package com.mic.product.infraestructure;


import com.mic.product.application.ProductServiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.util.List;

@AllArgsConstructor
@Log4j2
@Repository
public class ProductRepositoryAdapter implements ProductServiceRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryAdapter.class);
    private ProductRepository productRepository;

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        String sku = productEntity.getSku();
        ProductEntity productEntitySave;
        this.logRuntimeStartCreate(sku);
        try {
            productEntitySave = productRepository.save(productEntity);
            this.logExternalSuccessCreate(productEntity);
        } catch (Exception e) {
            this.logExternalFailedCreate(sku, e);
            throw e;
        }
        this.logRuntimeEndCreate();
        return productEntitySave;
    }

    @Override
    public ProductEntity updateProduct(ProductEntity productEntity) {

        String sku = productEntity.getSku();
        ProductEntity productEntityUpdate;
        this.logRuntimeStartUpdate(sku);
        try {
            productEntityUpdate = productRepository.save(productEntity);
            this.logExternalSuccessUpdate(productEntityUpdate);
        } catch (Exception e) {
            this.logExternalFailedUpdate(sku,e);
            throw e;
        }
        this.logRuntimeEndUpdate();
        return productEntityUpdate;
    }

    @Override
    public void deleteProduct(String sku) {
        this.logRuntimeStartDelete();
        try {
            productRepository.deleteBySku(sku);
            this.logExternalSuccessDelete(sku);
        } catch (Exception e) {
            this.logExternalFailedDelete(sku,e);
            throw e;
        }
        this.logRuntimeEndDelete();
    }

    @Override
    public ProductEntity getProductBySku(String sku) {
        this.logRuntimeStartGet(sku);
        ProductEntity productEntity;
        try {
            productEntity =  productRepository.findBySku(sku);
            this.logExternalSuccessGet(productEntity);
        } catch (Exception e) {
            this.logExternalFailedGet(sku, e);
            throw e;
        }
        this.logRuntimeEndGet();
        return productEntity;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        this.logRuntimeStartGetAll();
        List<ProductEntity> productEntityList;
        try {
            productEntityList = productRepository.findAll();
            this.logExternalSuccessGetAll();
        } catch (Exception e) {
            this.logExternalFailedGetAll(e);
            throw e;
        }
        this.logRuntimeEndGetAll();
        return productEntityList;
    }

    @Override
    public Boolean existsBySku(String sku) {
        this.logRuntimeStartExist(sku);
        boolean exists;
        try {
            exists = productRepository.existsBySku(sku);
            this.logExternalSuccessExist(exists);
        } catch (Exception e) {
            this.logExternalFailedExist(sku,e);
            throw e;
        }
        this.logRuntimeEndExist();
        return exists;
    }

    // ***************
    // **** logs  ****
    // ***************


    // ***************
    // **** exist ****
    // ***************

    public static final String EXIST_RUNTIME_START_MSG = "Start checking Product exist with sku: {}";
    public static final String EXIST_RUNTIME_END_MSG = "End checking Product exist";
    public static final String EXIST_EXTERNAL_SUCCESS_MSG = "The result of exist Product is: {}";
    public static final String EXIST_EXTERNAL_FAILED_MSG = "Error call to DB to check if the Product with sku: {} exist";

    protected void logRuntimeStartExist(final String sku) {
        logger.debug(EXIST_RUNTIME_START_MSG, sku);
    }

    protected void logRuntimeEndExist() {
        logger.debug(EXIST_RUNTIME_END_MSG);
    }

    protected void logExternalSuccessExist(final boolean exists) {
        logger.debug(EXIST_EXTERNAL_SUCCESS_MSG, exists);
    }

    protected void logExternalFailedExist(final String sku, final Exception e) {
        logger.error(EXIST_EXTERNAL_FAILED_MSG, sku, e);
    }


    // *****************
    // **** get all ****
    // *****************

    public static final String GET_ALL_RUNTIME_START_MSG = "Start GET all data of Product in DB";
    public static final String GET_ALL_RUNTIME_END_MSG = "End GET ALL data of Product in DB";
    public static final String GET_ALL_EXTERNAL_SUCCESS_MSG = "The result of exist Products";
    public static final String GET_ALL_EXTERNAL_FAILED_MSG = "Error call to GET ALL DB to data Products with error: ";

    protected void logRuntimeStartGetAll() {
        logger.debug(GET_ALL_RUNTIME_START_MSG);
    }

    protected void logRuntimeEndGetAll() {
        logger.debug(GET_ALL_RUNTIME_END_MSG);
    }

    protected void logExternalSuccessGetAll() {
        logger.debug(GET_ALL_EXTERNAL_SUCCESS_MSG);
    }

    protected void logExternalFailedGetAll(final Exception e) {
        logger.error(GET_ALL_EXTERNAL_FAILED_MSG, e);
    }

    // *****************
    // **** get ********
    // *****************

    public static final String GET_RUNTIME_START_MSG = "Start GET data of Product in DB whit SKU: {}";
    public static final String GET_RUNTIME_END_MSG = "End GET data of Product in DB";
    public static final String GET_EXTERNAL_SUCCESS_MSG = "The result of exist Product : {}";
    public static final String GET__EXTERNAL_FAILED_MSG = "Error call to GET DB to data Products with error: {}";

    protected void logRuntimeStartGet(final String sku) {
        logger.debug(GET_RUNTIME_START_MSG, sku);
    }

    protected void logRuntimeEndGet() {
        logger.debug(GET_RUNTIME_END_MSG);
    }

    protected void logExternalSuccessGet(final Object product) {
        logger.debug(GET_EXTERNAL_SUCCESS_MSG, product);
    }

    protected void logExternalFailedGet(final String sku, final Exception e) {
        logger.error(GET__EXTERNAL_FAILED_MSG, sku, e);
    }
    // *****************
    // **** delete *****
    // *****************

    public static final String DELETE_RUNTIME_START_MSG = "Start DELETE data of Product in DB";
    public static final String DELETE_RUNTIME_END_MSG = "End DELETE data of Product in DB";
    public static final String DELETE_EXTERNAL_SUCCESS_MSG = "The product with SKU: {} has been deleted successfully";
    public static final String DELETE_EXTERNAL_FAILED_MSG = "Error call DELETE DB to data Products with error: {}";

    protected void logRuntimeStartDelete() {
        logger.debug(DELETE_RUNTIME_START_MSG);
    }

    protected void logRuntimeEndDelete() {
        logger.debug(DELETE_RUNTIME_END_MSG);
    }

    protected void logExternalSuccessDelete(final String sku) {
        logger.debug(DELETE_EXTERNAL_SUCCESS_MSG, sku);
    }

    protected void logExternalFailedDelete(final String sku, final Exception e) {
        logger.error(DELETE_EXTERNAL_FAILED_MSG, sku, e);
    }
    // *****************
    // **** update *****
    // *****************

    public static final String UPDATE_RUNTIME_START_MSG = "Start UPDATE data of Product in DB whit SKU: {}";
    public static final String UPDATE_RUNTIME_END_MSG = "End UPDATE data of Product in DB";
    public static final String UPDATE_EXTERNAL_SUCCESS_MSG = "The product has been updated successfully: {}";
    public static final String UPDATE_EXTERNAL_FAILED_MSG = "Error call UPDATE DB to data Products with error: {}";

    protected void logRuntimeStartUpdate(final String sku) {
        logger.debug(UPDATE_RUNTIME_START_MSG, sku);
    }

    protected void logRuntimeEndUpdate() {
        logger.debug(UPDATE_RUNTIME_END_MSG);
    }

    protected void logExternalSuccessUpdate(final Object product) {
        logger.debug(UPDATE_EXTERNAL_SUCCESS_MSG, product);
    }

    protected void logExternalFailedUpdate(final String sku, final Exception e) {
        logger.error(UPDATE_EXTERNAL_FAILED_MSG, sku, e);
    }

    // *****************
    // **** create *****
    // *****************

    public static final String CREATE_RUNTIME_START_MSG = "Start CREATE data of Product in DB whit SKU: {}";
    public static final String CREATE_RUNTIME_END_MSG = "End CREATE data of Product in DB";
    public static final String CREATE_EXTERNAL_SUCCESS_MSG = "The product has been create successfully : {}";
    public static final String CREATE_EXTERNAL_FAILED_MSG = "Error call CREATE DB to data Products with error: {}";

    protected void logRuntimeStartCreate(final String sku) {
        logger.debug(CREATE_RUNTIME_START_MSG, sku);
    }

    protected void logRuntimeEndCreate() {
        logger.debug(CREATE_RUNTIME_END_MSG);
    }

    protected void logExternalSuccessCreate(final Object product) {
        logger.debug(CREATE_EXTERNAL_SUCCESS_MSG, product);
    }

    protected void logExternalFailedCreate(final String sku, final Exception e) {
        logger.error(CREATE_EXTERNAL_FAILED_MSG, sku, e);
    }
}
