package ui.adpter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.erp.R;

import java.util.List;

import model.javabean.FinancialSalaryStatistics;

/**
 * Created by admin on 2017/8/14.
 */

public class FinancialSalaryStatisticsAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<FinancialSalaryStatistics> list;
    private LayoutInflater inflater;
    public FinancialSalaryStatisticsAdapter(Activity activityBilling, List<FinancialSalaryStatistics> list) {
        Log.v("test2", "TimeBillingStatisticsAdapter");
        inflater = LayoutInflater.from(activityBilling);
        this.activity = activityBilling;
        this.list = list;
        Log.d("FinancialTimeBillingSta", "adpater" + list.size());
    }
    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }
    public void updateView(List<FinancialSalaryStatistics> list)
    {
        this.list = list;
        this.notifyDataSetChanged();//强制动态刷新数据进而调用getView方法
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("test2", "getView");
        FinancialSalaryStatisticsAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new FinancialSalaryStatisticsAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.salary_item_data, null);
            viewHolder.year = (TextView) convertView.findViewById(R.id.year);//年
            viewHolder.month = (TextView) convertView.findViewById(R.id.month);//月份
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);//员工姓名
            viewHolder.baseSalary = (TextView) convertView.findViewById(R.id.base_salary);//基本工资
            viewHolder.tralAllowance = (TextView) convertView.findViewById(R.id.tral_Allowance);//出差补助
            viewHolder.bonuxWage = (TextView) convertView.findViewById(R.id.bonux_wage);//奖金工资
            viewHolder.travelAllowance = (TextView) convertView.findViewById(R.id.travel_allowance);//交通补助
            viewHolder.overtimePremium = (TextView) convertView.findViewById(R.id.overtime_premium);//加班补助
            viewHolder.reimburse = (TextView) convertView.findViewById(R.id.reimburse);//报销
            viewHolder.grossIncome = (TextView) convertView.findViewById(R.id.gross_income);//全额工资
            viewHolder.sickLeavePay = (TextView) convertView.findViewById(R.id.sick_leave_pay);//病事假工资
            viewHolder.housingFund = (TextView) convertView.findViewById(R.id.housing_fund);//住房公积金
            viewHolder.medicalInsurance = (TextView) convertView.findViewById(R.id.medical_insurance);//养老保险
            viewHolder.unemploymentInsurance = (TextView) convertView.findViewById(R.id.unemployment_insurance);//医疗保险
            viewHolder.taxableIncome = (TextView) convertView.findViewById(R.id.taxable_income);//失业保险
            viewHolder.endowmentInsurance = (TextView) convertView.findViewById(R.id.endowment_insurance);//应税所得额
            viewHolder.personalIncomeTax = (TextView) convertView.findViewById(R.id.personal_income_tax);//个税
            viewHolder.netPayment = (TextView) convertView.findViewById(R.id.net_payment);//实发工资
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FinancialSalaryStatisticsAdapter.ViewHolder) convertView.getTag();
        }
        Log.d("FinancialTimeBillingSta", "year:" + list.get(position).getYear());
        viewHolder.year.setText(this.list.get(position).getYear());
        viewHolder.year.setTextSize(13);
        viewHolder.month.setText(this.list.get(position).getMonth());
        viewHolder.month.setTextSize(13);
        viewHolder.name.setText(this.list.get(position).getUserName());
        viewHolder.name.setTextSize(13);
        viewHolder.baseSalary.setText(this.list.get(position).getBasePay());
        viewHolder.baseSalary.setTextSize(13);
        viewHolder.tralAllowance.setText(this.list.get(position).getTrafficAllowance());
        viewHolder.tralAllowance.setTextSize(13);
        viewHolder.bonuxWage.setText(this.list.get(position).getBonus());
        viewHolder.bonuxWage.setTextSize(13);
        viewHolder.travelAllowance.setText(this.list.get(position).getTravellingAllowance());
        viewHolder.travelAllowance.setTextSize(13);
        viewHolder.overtimePremium.setText(this.list.get(position).getOvertimeAllowance());
        viewHolder.overtimePremium.setTextSize(13);
        viewHolder.reimburse.setText(this.list.get(position).getExpense());
        viewHolder.reimburse.setTextSize(13);
        viewHolder.grossIncome.setText(this.list.get(position).getFullPay());
        viewHolder.grossIncome.setTextSize(13);
        viewHolder.sickLeavePay.setText(this.list.get(position).getLeavePay());
        viewHolder.sickLeavePay.setTextSize(13);
        viewHolder.housingFund.setText(this.list.get(position).getHousingFund());
        viewHolder.housingFund.setTextSize(13);
        viewHolder.medicalInsurance.setText(this.list.get(position).getEndowmentInsurance());
        viewHolder.medicalInsurance.setTextSize(13);
        viewHolder.unemploymentInsurance.setText(this.list.get(position).getMedicalInsurance());
        viewHolder.unemploymentInsurance.setTextSize(13);
        viewHolder.taxableIncome.setText(this.list.get(position).getUnemploymentInsurance());
        viewHolder.taxableIncome.setTextSize(13);
        viewHolder.endowmentInsurance.setText(this.list.get(position).getTaxableIncome());
        viewHolder.endowmentInsurance.setTextSize(13);
        viewHolder.personalIncomeTax.setText(this.list.get(position).getNdividualIncomeTax());
        viewHolder.personalIncomeTax.setTextSize(13);
        viewHolder.netPayment.setText(this.list.get(position).getNetPayroll());
        viewHolder.netPayment.setTextSize(13);
        return convertView;
    }

    public static class ViewHolder {
        public TextView id,year,month,name,baseSalary,tralAllowance,bonuxWage,travelAllowance,overtimePremium,reimburse,grossIncome,sickLeavePay
                ,housingFund,medicalInsurance,unemploymentInsurance,taxableIncome,endowmentInsurance,personalIncomeTax,netPayment;
    }
}

