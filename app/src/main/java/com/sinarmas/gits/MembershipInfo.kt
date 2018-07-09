package com.sinarmas.gits

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sinarmas.gits.adapter.CustomGridMembershipAdapter
import kotlinx.android.synthetic.main.activity_membership_info.*

class MembershipInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership_info)

        back.setOnClickListener {
            finish()
        }

        val voucher = arrayOf("1", "2", "3", "4", "5")
        gridMembership.adapter = CustomGridMembershipAdapter(this, voucher)

    }
}
