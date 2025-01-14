package it.firegloves.mempoi.builder;


import it.firegloves.mempoi.styles.MempoiStyler;
import it.firegloves.mempoi.styles.template.StyleTemplate;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Optional;

public class MempoiStylerBuilder {

    private Workbook workbook;

    private StyleTemplate styleTemplate;
    private CellStyle headerCellStyle;
    private CellStyle subFooterCellStyle;
    private CellStyle commonDataCellStyle;
    private CellStyle dateCellStyle;
    private CellStyle datetimeCellStyle;
    private CellStyle numberCellStyle;

    /**
     * private constructor to lower constructor visibility from outside forcing the use of the static Builder pattern
     */
    private MempoiStylerBuilder(Workbook workbook) {
        this.workbook = workbook;
    }

    /**
     * static method to create a new MempoiStylerBuilder containing the received Workbook
     * @param workbook the workbook to associate to the MempoiStylerBuilder
     *
     * @return the MempoiStylerBuilder created
     */
    public static MempoiStylerBuilder aMempoiStyler(Workbook workbook) {
        return new MempoiStylerBuilder(workbook);
    }

    /**
     * add the received StyleTemplate to the builder instance
     * @param styleTemplate the StyleTemplate to use generating the Mempoi report
     *
     * @return the current MempoiStylerBuilder
     */
    public MempoiStylerBuilder withStyleTemplate(StyleTemplate styleTemplate) {
        this.styleTemplate = styleTemplate;
        return this;
    }

    /**
     * add the received CellStyle as HeaderCell styler to the builder instance
     * @param headerCellStyle the CellStyle to set as HeaderCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    public MempoiStylerBuilder withHeaderCellStyle(CellStyle headerCellStyle) {
        this.headerCellStyle = null != headerCellStyle ? headerCellStyle : this.styleTemplate.getHeaderCellStyle(this.workbook);
        return this;
    }

    /**
     * add the received CellStyle as SubFooterCell styler to the builder instance
     * @param subFooterCellStyle the CellStyle to set as SubFooterCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    public MempoiStylerBuilder withSubFooterCellStyle(CellStyle subFooterCellStyle) {
        this.subFooterCellStyle = null != subFooterCellStyle ? subFooterCellStyle : this.styleTemplate.getSubfooterCellStyle(this.workbook);
        return this;
    }

    /**
     * add the received CellStyle as CommonDataCell styler to the builder instance
     * @param commonDataCellStyle the CellStyle to set as CommonDataCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    public MempoiStylerBuilder withCommonDataCellStyle(CellStyle commonDataCellStyle) {
        this.commonDataCellStyle = null != commonDataCellStyle ? commonDataCellStyle : this.styleTemplate.getCommonDataCellStyle(this.workbook);
        return this;
    }

    /**
     * add the received CellStyle as DateCell styler to the builder instance
     * @param dateCellStyle the CellStyle to set as DateCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    public MempoiStylerBuilder withDateCellStyle(CellStyle dateCellStyle) {
        this.dateCellStyle = null != dateCellStyle ? dateCellStyle : this.styleTemplate.getDateCellStyle(this.workbook);
        return this;
    }

    /**
     *add the received CellStyle as DatetimeCell styler to the builder instance
     * @param datetimeCellStyle the CellStyle to set as DatetimeCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    public MempoiStylerBuilder withDatetimeCellStyle(CellStyle datetimeCellStyle) {
        this.datetimeCellStyle = null != datetimeCellStyle ? datetimeCellStyle : this.styleTemplate.getDatetimeCellStyle(this.workbook);
        return this;
    }

    /**
     * add the received CellStyle as NumberCell styler to the builder instance
     * @param numberCellStyle the CellStyle to set as NumberCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    public MempoiStylerBuilder withNumberCellStyle(CellStyle numberCellStyle) {
        this.numberCellStyle = null != numberCellStyle ? numberCellStyle : this.styleTemplate.getNumberCellStyle(this.workbook);
        return this;
    }

    /**
     * builds the MempoiStyler and returns it
     * if some cellstyler are not specified it fallbacks on style template
     * if no styleTemplate is specified it fallbacks on StandardStyleTemplate
     *
     * used for WorkbookConfiguration
     *
     * @return an Optional containing the resulting MempoiStyler
     */
    public Optional<MempoiStyler> build() {

        MempoiStyler styler = null;

        if (null != this.styleTemplate ||
                null != this.headerCellStyle ||
                null != this.dateCellStyle ||
                null != this.datetimeCellStyle ||
                null != this.numberCellStyle ||
                null != this.commonDataCellStyle ||
                null != this.subFooterCellStyle) {

            styler = new MempoiStyler();

            // customize styles
            styler.setHeaderCellStyle(Optional.ofNullable(this.headerCellStyle).orElseGet(() -> this.styleTemplate.getHeaderCellStyle(this.workbook)));
            styler.setDateCellStyle(Optional.ofNullable(this.dateCellStyle).orElseGet(() -> this.styleTemplate.getDateCellStyle(this.workbook)));
            styler.setDatetimeCellStyle(Optional.ofNullable(this.datetimeCellStyle).orElseGet(() -> this.styleTemplate.getDatetimeCellStyle(this.workbook)));
            styler.setNumberCellStyle(Optional.ofNullable(this.numberCellStyle).orElseGet(() -> this.styleTemplate.getNumberCellStyle(this.workbook)));
            styler.setCommonDataCellStyle(Optional.ofNullable(this.commonDataCellStyle).orElseGet(() -> this.styleTemplate.getCommonDataCellStyle(this.workbook)));
            styler.setSubFooterCellStyle(Optional.ofNullable(this.subFooterCellStyle).orElseGet(() -> this.styleTemplate.getSubfooterCellStyle(this.workbook)));

        }

        return Optional.ofNullable(styler);
    }





    /**
     * @deprecated Replaced by {@link #withStyleTemplate(StyleTemplate)}
     * @param styleTemplate the StyleTemplate to use generating the Mempoi report
     *
     * @return the current MempoiStylerBuilder
     */
    @Deprecated
    public MempoiStylerBuilder setStyleTemplate(StyleTemplate styleTemplate) {
        return this.withStyleTemplate(styleTemplate);
    }

    /**
     * @deprecated Replaced by {@link #withHeaderCellStyle(CellStyle)}
     * @param headerCellStyle the CellStyle to set as HeaderCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    @Deprecated
    public MempoiStylerBuilder setHeaderCellStyle(CellStyle headerCellStyle) {
        return this.withHeaderCellStyle(headerCellStyle);
    }

    /**
     * @deprecated Replaced by {@link #withSubFooterCellStyle(CellStyle)}
     * @param subFooterCellStyle the CellStyle to set as SubFooterCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    @Deprecated
    public MempoiStylerBuilder setSubFooterCellStyle(CellStyle subFooterCellStyle) {
        return this.withSubFooterCellStyle(subFooterCellStyle);
    }

    /**
     * @deprecated Replaced by {@link #withCommonDataCellStyle(CellStyle)}
     * @param commonDataCellStyle the CellStyle to set as CommonDataCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    @Deprecated
    public MempoiStylerBuilder setCommonDataCellStyle(CellStyle commonDataCellStyle) {
        return this.withCommonDataCellStyle(commonDataCellStyle);
    }

    /**
     * @deprecated Replaced by {@link #withDateCellStyle(CellStyle)}
     * @param dateCellStyle the CellStyle to set as DateCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    @Deprecated
    public MempoiStylerBuilder setDateCellStyle(CellStyle dateCellStyle) {
        return this.withDateCellStyle(dateCellStyle);
    }

    /**
     * @deprecated Replaced by {@link #withDatetimeCellStyle(CellStyle)}
     * @param datetimeCellStyle the CellStyle to set as DatetimeCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    @Deprecated
    public MempoiStylerBuilder setDatetimeCellStyle(CellStyle datetimeCellStyle) {
        return this.withDatetimeCellStyle(datetimeCellStyle);
    }

    /**
     * @deprecated Replaced by {@link #withNumberCellStyle(CellStyle)}
     * @param numberCellStyle the CellStyle to set as NumberCell styler
     *
     * @return the current MempoiStylerBuilder
     */
    @Deprecated
    public MempoiStylerBuilder setNumberCellStyle(CellStyle numberCellStyle) {
        return this.withNumberCellStyle(numberCellStyle);
    }

}