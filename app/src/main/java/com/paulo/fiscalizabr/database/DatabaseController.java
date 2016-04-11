package com.paulo.fiscalizabr.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import com.paulo.fiscalizabr.R;
import com.paulo.fiscalizabr.core.Convenio;
import com.paulo.fiscalizabr.core.DadosConvenio;
import com.paulo.fiscalizabr.core.OrgaoConcedente;
import com.paulo.fiscalizabr.core.OrgaoSuperior;
import com.paulo.fiscalizabr.core.Proponente;
import com.paulo.fiscalizabr.core.Proposta;
import com.paulo.fiscalizabr.core.ValorConvenio;
import com.paulo.fiscalizabr.tools.StringsTreatment;

import java.util.ArrayList;

/**
 * Created by Paulo on 08/04/2016.
 */
public class DatabaseController {

    private SQLiteDatabase database;
    private DatabaseHelper mDbHelper;
    private Context context;

    public DatabaseController(Context context) {
        mDbHelper = new DatabaseHelper(context);
        database = mDbHelper.getWritableDatabase();
        this.context = context;
    }

    public void addConvenio(Convenio convenio) {
        // Se for favorito só atualiza os dados no banco. Não irá duplicar a tupla de dados
        if(isFavorito(convenio.getNumeroConvenio())) {
            atualizaConvenio(convenio.getNumeroConvenio(), convenio);
        } else {
            ContentValues values = new ContentValues();

            values.put(Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO, convenio.getNumeroConvenio());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_OBJETO_CONVENIO, convenio.getObjetoConvenio());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_VIGENCIA, convenio.getVigencia());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_MUNICIPIO, convenio.getMunicipio());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_UF, convenio.getUf());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_NOME_PROPONENTE, convenio.getNomeProponente());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_VALOR_CONVENIO, convenio.getValorConvenio()); // Valor Global
            values.put(Contract.ConvenioEntry.COLUMN_NAME_SITUACAO_CONVENIO, convenio.getSituacaoConvenio());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO, convenio.isFavorito());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_MES_VIGENCIA, convenio.getMesVigencia());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_ANO_VIGENCIA, convenio.getAnoVigencia());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_MES_FINAL_VIGENCIA, convenio.getMesFinalVigencia());
            values.put(Contract.ConvenioEntry.COLUMN_NAME_ANO_FINAL_VIGENCIA, convenio.getAnoFinalVigencia());

            database.insert(
                    Contract.ConvenioEntry.TABLE_NAME,
                    Contract.COLUMN_NAME_NULLABLE,
                    values);
        }
    }

    public void atualizaConvenio(String numeroConvenio, Convenio convenio) {
        ContentValues values = new ContentValues();

        values.put(Contract.ConvenioEntry.COLUMN_NAME_OBJETO_CONVENIO, convenio.getObjetoConvenio());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_VIGENCIA, convenio.getVigencia());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_MUNICIPIO, convenio.getMunicipio());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_UF, convenio.getUf());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_NOME_PROPONENTE, convenio.getNomeProponente());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_VALOR_CONVENIO, convenio.getValorConvenio()); // Valor Global
        values.put(Contract.ConvenioEntry.COLUMN_NAME_SITUACAO_CONVENIO, convenio.getSituacaoConvenio());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_MES_VIGENCIA, convenio.getMesVigencia());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_ANO_VIGENCIA, convenio.getAnoVigencia());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_MES_FINAL_VIGENCIA, convenio.getMesFinalVigencia());
        values.put(Contract.ConvenioEntry.COLUMN_NAME_ANO_FINAL_VIGENCIA, convenio.getAnoFinalVigencia());

        database.update(
                Contract.ConvenioEntry.TABLE_NAME,
                values,
                Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO + " = ?",
                new String[]{numeroConvenio});
    }

    public void atualizaConvenioCompleto(String numeroConvenio, DadosConvenio convenioCompleto) {
        ContentValues values = new ContentValues();

        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_ASSINATURA, convenioCompleto.getAnoAssinatura());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_CONVENIO, convenioCompleto.getAnoConvenio());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PUBLICACAO, convenioCompleto.getAnoAssinatura());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_ASSINATURA, convenioCompleto.getDataAssinatura());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_PUBLICACAO, convenioCompleto.getDataPublicacao());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_FIM_VIGENCIA, convenioCompleto.getFimVigencia());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_INICIO_VIGENCIA, convenioCompleto.getInicioVigencia());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_JUSTIFICATIVA, convenioCompleto.getJustificativa());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_MES_ASSINATURA, convenioCompleto.getMesAssinatura());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_MES_PUBLICACAO, convenioCompleto.getMesPublicacao());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_MODALIDADE, convenioCompleto.getModalidade());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROGRAMA, convenioCompleto.getNomePrograma());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_PROCESSO, convenioCompleto.getNumeroProcesso());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_OBJETO, convenioCompleto.getObjeto());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_PUBLICACAO_CONVENIO, convenioCompleto.getSituacaoPublicacaoConvenio());

        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_CONCEDENTE, convenioCompleto.getOrgaoConcedente().getCargoResponsavelConcedente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_ORGAO_CONCEDENTE, convenioCompleto.getOrgaoConcedente().getNomeOrgaoConcedente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_CONCEDENTE, convenioCompleto.getOrgaoConcedente().getNomeResponsavelConcedente());

        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CODIGO_ORGAO_SUPERIOR, convenioCompleto.getOrgaoSuperior().getCodigoOrgaoSuperior());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_ORGAO_SUPERIOR, convenioCompleto.getOrgaoSuperior().getNomeOrgaoSuperior());

        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_BAIRRO_PROPONENTE, convenioCompleto.getProponente().getBairroProponente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_PROPONENTE, convenioCompleto.getProponente().getCargoResponsavelProponente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CEP_PROPONENTE, convenioCompleto.getProponente().getCepProponente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CODIGO_RESPONSAVEL_PROPONENTE, convenioCompleto.getProponente().getCodigoResponsavelProponente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ENDERECO_PROPONENTE, convenioCompleto.getProponente().getEnderecoProponente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ESFERA_ADMINISTRATIVA, convenioCompleto.getProponente().getEsferaAdministrativa());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_IDENTIFICACAO_PROPONENTE, convenioCompleto.getProponente().getIdentificacaoProponente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_MUNICIPIO, convenioCompleto.getProponente().getMunicipio());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROPONENTE, convenioCompleto.getProponente().getNomeProponente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_PROPONENTE, convenioCompleto.getProponente().getNomeResponsavelProponente());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_QUALIFICACAO, convenioCompleto.getProponente().getQualificacao());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_REGIAO, convenioCompleto.getProponente().getRegiao());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_UF, convenioCompleto.getProponente().getUf());

        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PROPOSTA, convenioCompleto.getProposta().getAnoProposta());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_INCLUSAO_PROPOSTA, convenioCompleto.getProposta().getDataInclusao());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_IDENTIFICACAO_PROPOSTA, convenioCompleto.getProposta().getIdentificacaoProposta());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_PROPOSTA, convenioCompleto.getProposta().getNumeroProposta());

        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_ADITIVOS, convenioCompleto.getQuantidadeAditivos());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_EMPENHOS, convenioCompleto.getQuantidadeEmpenhos());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_PRORROGAS, convenioCompleto.getQuantidadeProrrogas());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_CONVENIO, convenioCompleto.getSituacaoConvenio());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_SUBSITUACAO_CONVENIO, convenioCompleto.getSubsituacaoConvenio());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ULTIMO_PAGAMENTO, convenioCompleto.getUltimoPagamento());

        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS, convenioCompleto.getValorConvenio().getValorContrapartidaFinanceiraBensEServicos());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_DESEMBOLSADO, convenioCompleto.getValorConvenio().getValorDesembolsado());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_EMPENHADO, convenioCompleto.getValorConvenio().getValorEmpenhado());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_GLOBAL, convenioCompleto.getValorConvenio().getValorGlobal());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_REPASSE_UNIAO, convenioCompleto.getValorConvenio().getValorRepasseUniao());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_TOTAL_CONTRAPARTIDA, convenioCompleto.getValorConvenio().getValorTotalContrapartida());
        values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA, convenioCompleto.getValorConvenio().getValorContrapartidaFinanceira());

        database.update(
                Contract.ConvenioCompletoEntry.TABLE_NAME,
                values,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_CONVENIO + " = ?",
                new String[]{numeroConvenio});
    }

    public void addConvenioCompleto(DadosConvenio convenioCompleto) {
        if(isFavorito(convenioCompleto.getNumeroConvenio())) {
            atualizaConvenioCompleto(convenioCompleto.getNumeroConvenio(), convenioCompleto);
        } else {
            ContentValues values = new ContentValues();

            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_ASSINATURA, convenioCompleto.getAnoAssinatura());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_CONVENIO, convenioCompleto.getAnoConvenio());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PUBLICACAO, convenioCompleto.getAnoAssinatura());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_ASSINATURA, convenioCompleto.getDataAssinatura());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_PUBLICACAO, convenioCompleto.getDataPublicacao());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_FIM_VIGENCIA, convenioCompleto.getFimVigencia());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_INICIO_VIGENCIA, convenioCompleto.getInicioVigencia());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_JUSTIFICATIVA, convenioCompleto.getJustificativa());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_MES_ASSINATURA, convenioCompleto.getMesAssinatura());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_MES_PUBLICACAO, convenioCompleto.getMesPublicacao());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_MODALIDADE, convenioCompleto.getModalidade());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROGRAMA, convenioCompleto.getNomePrograma());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_CONVENIO, convenioCompleto.getNumeroConvenio());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_PROCESSO, convenioCompleto.getNumeroProcesso());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_OBJETO, convenioCompleto.getObjeto());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_PUBLICACAO_CONVENIO, convenioCompleto.getSituacaoPublicacaoConvenio());

            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_CONCEDENTE, convenioCompleto.getOrgaoConcedente().getCargoResponsavelConcedente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_ORGAO_CONCEDENTE, convenioCompleto.getOrgaoConcedente().getNomeOrgaoConcedente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_CONCEDENTE, convenioCompleto.getOrgaoConcedente().getNomeResponsavelConcedente());

            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CODIGO_ORGAO_SUPERIOR, convenioCompleto.getOrgaoSuperior().getCodigoOrgaoSuperior());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_ORGAO_SUPERIOR, convenioCompleto.getOrgaoSuperior().getNomeOrgaoSuperior());

            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_BAIRRO_PROPONENTE, convenioCompleto.getProponente().getBairroProponente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_PROPONENTE, convenioCompleto.getProponente().getCargoResponsavelProponente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CEP_PROPONENTE, convenioCompleto.getProponente().getCepProponente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_CODIGO_RESPONSAVEL_PROPONENTE, convenioCompleto.getProponente().getCodigoResponsavelProponente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ENDERECO_PROPONENTE, convenioCompleto.getProponente().getEnderecoProponente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ESFERA_ADMINISTRATIVA, convenioCompleto.getProponente().getEsferaAdministrativa());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_IDENTIFICACAO_PROPONENTE, convenioCompleto.getProponente().getIdentificacaoProponente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_MUNICIPIO, convenioCompleto.getProponente().getMunicipio());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROPONENTE, convenioCompleto.getProponente().getNomeProponente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_PROPONENTE, convenioCompleto.getProponente().getNomeResponsavelProponente());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_QUALIFICACAO, convenioCompleto.getProponente().getQualificacao());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_REGIAO, convenioCompleto.getProponente().getRegiao());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_UF, convenioCompleto.getProponente().getUf());

            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PROPOSTA, convenioCompleto.getProposta().getAnoProposta());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_INCLUSAO_PROPOSTA, convenioCompleto.getProposta().getDataInclusao());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_IDENTIFICACAO_PROPOSTA, convenioCompleto.getProposta().getIdentificacaoProposta());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_PROPOSTA, convenioCompleto.getProposta().getNumeroProposta());

            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_ADITIVOS, convenioCompleto.getQuantidadeAditivos());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_EMPENHOS, convenioCompleto.getQuantidadeEmpenhos());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_PRORROGAS, convenioCompleto.getQuantidadeProrrogas());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_CONVENIO, convenioCompleto.getSituacaoConvenio());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_SUBSITUACAO_CONVENIO, convenioCompleto.getSubsituacaoConvenio());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_ULTIMO_PAGAMENTO, convenioCompleto.getUltimoPagamento());

            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS, convenioCompleto.getValorConvenio().getValorContrapartidaFinanceiraBensEServicos());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_DESEMBOLSADO, convenioCompleto.getValorConvenio().getValorDesembolsado());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_EMPENHADO, convenioCompleto.getValorConvenio().getValorEmpenhado());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_GLOBAL, convenioCompleto.getValorConvenio().getValorGlobal());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_REPASSE_UNIAO, convenioCompleto.getValorConvenio().getValorRepasseUniao());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_TOTAL_CONTRAPARTIDA, convenioCompleto.getValorConvenio().getValorTotalContrapartida());
            values.put(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA, convenioCompleto.getValorConvenio().getValorContrapartidaFinanceira());

            database.insert(
                    Contract.ConvenioCompletoEntry.TABLE_NAME,
                    Contract.COLUMN_NAME_NULLABLE,
                    values);
        }
    }

    // Retorna todos os convênios do banco
    public ArrayList<Convenio> selectConvenios() {
        database = mDbHelper.getReadableDatabase();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String cidadePreference = sharedPrefs.getString(context.getString(R.string.preference_cidade), context.getString(R.string.default_cidade));
        String ufPreference = sharedPrefs.getString(context.getString(R.string.preference_uf), context.getString(R.string.default_uf));

        cidadePreference = new StringsTreatment().normalizaStringBanco(cidadePreference);
        ufPreference = new StringsTreatment().normalizaStringBanco(ufPreference);

        String[] convenios = {
                Contract.ConvenioEntry._ID,

                Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO,
                Contract.ConvenioEntry.COLUMN_NAME_OBJETO_CONVENIO,
                Contract.ConvenioEntry.COLUMN_NAME_VIGENCIA,
                Contract.ConvenioEntry.COLUMN_NAME_MUNICIPIO,
                Contract.ConvenioEntry.COLUMN_NAME_UF,
                Contract.ConvenioEntry.COLUMN_NAME_NOME_PROPONENTE,
                Contract.ConvenioEntry.COLUMN_NAME_VALOR_CONVENIO,
                Contract.ConvenioEntry.COLUMN_NAME_SITUACAO_CONVENIO,
                Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO,
                Contract.ConvenioEntry.COLUMN_NAME_MES_VIGENCIA,
                Contract.ConvenioEntry.COLUMN_NAME_ANO_VIGENCIA,
                Contract.ConvenioEntry.COLUMN_NAME_MES_FINAL_VIGENCIA,
                Contract.ConvenioEntry.COLUMN_NAME_ANO_FINAL_VIGENCIA
        };

        Cursor c = database.query(
                Contract.ConvenioEntry.TABLE_NAME,      // The table to query
                convenios,                               // The columns to return
                Contract.ConvenioEntry.COLUMN_NAME_MUNICIPIO + " = ? AND " +
                Contract.ConvenioEntry.COLUMN_NAME_UF + " = ?",                                   // The columns for the WHERE clause
                new String[]{String.valueOf(cidadePreference), String.valueOf(ufPreference)},                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );

        ArrayList<Convenio> resultado = new ArrayList<Convenio>();

        if(c.moveToFirst() == true) {

            c.moveToFirst();

            for(int i=0; i<c.getCount(); i++) {
                Convenio conv = new Convenio();

                conv.setNumeroConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO)));
                conv.setObjetoConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_OBJETO_CONVENIO)));
                conv.setVigencia(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_VIGENCIA)));
                conv.setMunicipio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_MUNICIPIO)));
                conv.setUf(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_UF)));
                conv.setNomeProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_NOME_PROPONENTE)));
                conv.setValorConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_VALOR_CONVENIO)));
                conv.setSituacaoConvenio(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_SITUACAO_CONVENIO)));

                conv.setIsFavorito(isFavorito(conv.getNumeroConvenio()));

                conv.setMesVigencia(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_MES_VIGENCIA)));
                conv.setAnoVigencia(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_ANO_VIGENCIA)));
                conv.setAnoFinalVigencia(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_ANO_FINAL_VIGENCIA)));
                conv.setMesFinalVigencia(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_MES_FINAL_VIGENCIA)));

                resultado.add(conv);

                c.moveToNext();
            }
        }

        c.close();

        return resultado;
    }

    // Retorna todos os convênios do banco
    public ArrayList<Convenio> selectFavoritos() {
        database = mDbHelper.getReadableDatabase();

        String[] convenios = {
                Contract.ConvenioEntry._ID,

                Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO,
                Contract.ConvenioEntry.COLUMN_NAME_OBJETO_CONVENIO,
                Contract.ConvenioEntry.COLUMN_NAME_VIGENCIA,
                Contract.ConvenioEntry.COLUMN_NAME_MUNICIPIO,
                Contract.ConvenioEntry.COLUMN_NAME_UF,
                Contract.ConvenioEntry.COLUMN_NAME_NOME_PROPONENTE,
                Contract.ConvenioEntry.COLUMN_NAME_VALOR_CONVENIO,
                Contract.ConvenioEntry.COLUMN_NAME_SITUACAO_CONVENIO,
                Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO,
                Contract.ConvenioEntry.COLUMN_NAME_MES_VIGENCIA,
                Contract.ConvenioEntry.COLUMN_NAME_ANO_VIGENCIA,
                Contract.ConvenioEntry.COLUMN_NAME_MES_FINAL_VIGENCIA,
                Contract.ConvenioEntry.COLUMN_NAME_ANO_FINAL_VIGENCIA
        };

        Cursor c = database.query(
                Contract.ConvenioEntry.TABLE_NAME,      // The table to query
                convenios,                               // The columns to return
                Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO + " = ?",     // The columns for the WHERE clause
                new String[]{String.valueOf("1")},                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );

        ArrayList<Convenio> resultado = new ArrayList<Convenio>();

        if(c.moveToFirst() == true) {

            c.moveToFirst();

            for(int i=0; i<c.getCount(); i++) {
                Convenio conv = new Convenio();

                conv.setNumeroConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO)));
                conv.setObjetoConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_OBJETO_CONVENIO)));
                conv.setIsFavorito(isFavorito(conv.getNumeroConvenio()));
                conv.setMunicipio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_MUNICIPIO)));
                conv.setUf(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_UF)));
                conv.setNomeProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_NOME_PROPONENTE)));
                conv.setVigencia(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_VIGENCIA)));
                conv.setValorConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_VALOR_CONVENIO)));
                conv.setSituacaoConvenio(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_SITUACAO_CONVENIO)));
                conv.setMesVigencia(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_MES_VIGENCIA)));
                conv.setAnoVigencia(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_ANO_VIGENCIA)));
                conv.setAnoFinalVigencia(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_ANO_FINAL_VIGENCIA)));
                conv.setMesFinalVigencia(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_MES_FINAL_VIGENCIA)));

                resultado.add(conv);

                c.moveToNext();
            }
        }

        c.close();

        return resultado;
    }

    public DadosConvenio selectConvenioCompleto(String numeroConvenio) {
        database = mDbHelper.getReadableDatabase();

        String[] convenios = {
                Contract.ConvenioCompletoEntry._ID,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_ASSINATURA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_CONVENIO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PUBLICACAO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_ASSINATURA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_PUBLICACAO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_FIM_VIGENCIA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_INICIO_VIGENCIA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_JUSTIFICATIVA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_MES_ASSINATURA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_MES_PUBLICACAO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_MODALIDADE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROGRAMA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_CONVENIO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_PROCESSO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_OBJETO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_PUBLICACAO_CONVENIO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_CONCEDENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_ORGAO_CONCEDENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_CONCEDENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_CODIGO_ORGAO_SUPERIOR,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_ORGAO_SUPERIOR,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_BAIRRO_PROPONENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_PROPONENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_CEP_PROPONENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_CODIGO_RESPONSAVEL_PROPONENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_ENDERECO_PROPONENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_ESFERA_ADMINISTRATIVA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_IDENTIFICACAO_PROPONENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_MUNICIPIO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROPONENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_PROPONENTE,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_QUALIFICACAO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_REGIAO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_UF,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PROPOSTA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_INCLUSAO_PROPOSTA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_IDENTIFICACAO_PROPOSTA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_PROPOSTA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_ADITIVOS,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_EMPENHOS,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_PRORROGAS,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_CONVENIO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_SUBSITUACAO_CONVENIO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_ULTIMO_PAGAMENTO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_DESEMBOLSADO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_EMPENHADO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_GLOBAL,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_REPASSE_UNIAO,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_TOTAL_CONTRAPARTIDA,
                Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA
        };

        Cursor c = database.query(
                Contract.ConvenioCompletoEntry.TABLE_NAME,      // The table to query
                convenios,                               // The columns to return
                Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_CONVENIO + " = ?",     // The columns for the WHERE clause
                new String[]{numeroConvenio},                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );

        DadosConvenio conv = new DadosConvenio();

        if(c.moveToFirst() == true) {

            c.moveToFirst();

                conv.setAnoAssinatura(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_ASSINATURA)));
                conv.setAnoConvenio(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_CONVENIO)));
                conv.setAnoPublicacao(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PUBLICACAO)));
                conv.setDataAssinatura(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_ASSINATURA)));
                conv.setDataPublicacao(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_PUBLICACAO)));
                conv.setFimVigencia(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_FIM_VIGENCIA)));
                conv.setInicioVigencia(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_INICIO_VIGENCIA)));
                conv.setJustificativa(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_JUSTIFICATIVA)));
                conv.setMesAssinatura(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_MES_ASSINATURA)));
                conv.setMesPublicacao(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_MES_PUBLICACAO)));
                conv.setModalidade(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_MODALIDADE)));
                conv.setNomePrograma(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROGRAMA)));
                conv.setNumeroConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_CONVENIO)));
                conv.setNumeroProcesso(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_PROCESSO)));
                conv.setObjeto(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_OBJETO)));
                conv.setSituacaoPublicacaoConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_PUBLICACAO_CONVENIO)));

                OrgaoConcedente conc = new OrgaoConcedente();
                conc.setCargoResponsavelConcedente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_CONCEDENTE)));
                conc.setNomeOrgaoConcedente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_ORGAO_CONCEDENTE)));
                conc.setNomeResponsavelConcedente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_CONCEDENTE)));

                conv.setOrgaoConcedente(conc);

                OrgaoSuperior sup = new OrgaoSuperior();
                sup.setCodigoOrgaoSuperior(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_CODIGO_ORGAO_SUPERIOR)));
                sup.setNomeOrgaoSuperior(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_ORGAO_SUPERIOR)));

                conv.setOrgaoSuperior(sup);

                Proponente prop = new Proponente();

                prop.setBairroProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_BAIRRO_PROPONENTE)));
                prop.setCargoResponsavelProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_CARGO_RESPONSAVEL_PROPONENTE)));
                prop.setCepProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_CEP_PROPONENTE)));
                prop.setCodigoResponsavelProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_CODIGO_RESPONSAVEL_PROPONENTE)));
                prop.setEnderecoProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_ENDERECO_PROPONENTE)));
                prop.setEsferaAdministrativa(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_ESFERA_ADMINISTRATIVA)));
                prop.setIdentificacaoProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_IDENTIFICACAO_PROPONENTE)));
                prop.setMunicipio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_MUNICIPIO)));
                prop.setNomeProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_PROPONENTE)));
                prop.setNomeResponsavelProponente(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NOME_RESPONSAVEL_PROPONENTE)));
                prop.setQualificacao(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_QUALIFICACAO)));
                prop.setRegiao(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_REGIAO)));
                prop.setUf(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_UF)));

                conv.setProponente(prop);

                Proposta pro = new Proposta();

                pro.setAnoProposta(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_ANO_PROPOSTA)));
                pro.setDataInclusao(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_DATA_INCLUSAO_PROPOSTA)));
                pro.setIdentificacaoProposta(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_IDENTIFICACAO_PROPOSTA)));
                pro.setNumeroProposta(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_NUMERO_PROPOSTA)));

                conv.setProposta(pro);

                conv.setQuantidadeAditivos(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_ADITIVOS)));
                conv.setQuantidadeEmpenhos(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_EMPENHOS)));
                conv.setQuantidadeProrrogas(c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_QTD_PRORROGAS)));
                conv.setSituacaoConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_SITUACAO_CONVENIO)));
                conv.setSubsituacaoConvenio(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_SUBSITUACAO_CONVENIO)));
                conv.setUltimoPagamento(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_ULTIMO_PAGAMENTO)));

                ValorConvenio valor = new ValorConvenio();

                valor.setValorContrapartidaFinanceiraBensEServicos(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS)));
                valor.setValorDesembolsado(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_DESEMBOLSADO)));
                valor.setValorEmpenhado(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_EMPENHADO)));
                valor.setValorGlobal(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_GLOBAL)));
                valor.setValorRepasseUniao(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_REPASSE_UNIAO)));
                valor.setValorTotalContrapartida(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_TOTAL_CONTRAPARTIDA)));
                valor.setValorContrapartidaFinanceira(c.getString(c.getColumnIndexOrThrow(Contract.ConvenioCompletoEntry.COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA)));

                conv.setValorConvenio(valor);
        }

        c.close();

        return conv;
    }

    public void deleteConvenios() {
        // Deleta todos os convenios que não foram favoritados
        ArrayList<Convenio> convenios = new ArrayList<Convenio>();
        convenios.addAll(selectConvenios());

        for(int i=0; i<convenios.size(); i++) {
            if(convenios.get(i).isFavorito() == false) {
                database.delete(Contract.ConvenioEntry.TABLE_NAME,
                        Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO + " = ?",
                        new String[]{convenios.get(i).getNumeroConvenio()});

                database.delete(Contract.ConvenioCompletoEntry.TABLE_NAME,
                        Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO + " = ?",
                        new String[]{convenios.get(i).getNumeroConvenio()});
            }
        }
    }

    public void addFavoritos(String numeroConvenio) {
        ContentValues values = new ContentValues();

        values.put(Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO, "1");

        database.update(
                Contract.ConvenioEntry.TABLE_NAME,
                values,
                Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO + " = ?",
                new String[]{numeroConvenio});
    }

    public void removeFavoritos(String numeroConvenio) {
        ContentValues values = new ContentValues();

        values.put(Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO, "0");

        database.update(
                Contract.ConvenioEntry.TABLE_NAME,
                values,
                Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO + " = ?",
                new String[]{numeroConvenio});
    }

    // Verifica se o convênio está favoritado
    public boolean isFavorito(String numeroConvenio) {
        database = mDbHelper.getReadableDatabase();

        String[] convenios = {
                Contract.ConvenioEntry._ID,
                Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO
        };

        Cursor c = database.query(
                Contract.ConvenioEntry.TABLE_NAME,      // The table to query
                convenios,                               // The columns to return
                Contract.ConvenioEntry.COLUMN_NAME_NUMERO_CONVENIO + " = ?",     // The columns for the WHERE clause
                new String[]{String.valueOf(numeroConvenio)},                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );

        Convenio resultado = new Convenio();

        if(c.moveToFirst() == true) {

            c.moveToFirst();

            int favorito = c.getInt(c.getColumnIndexOrThrow(Contract.ConvenioEntry.COLUMN_NAME_IS_FAVORITO));
            if (favorito == 1) return true;
            else return false;
        }

        c.close();

        return false;
    }

}
