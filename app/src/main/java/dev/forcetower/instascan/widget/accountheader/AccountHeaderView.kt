package dev.forcetower.instascan.widget.accountheader

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.instascan.databinding.WidgetAccountHeaderBinding

class AccountHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout (context, attrs, defStyleAttr) {
    private val binding = WidgetAccountHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    fun setAccount(acc: Account) {
        binding.apply {
            account = acc
            executePendingBindings()
        }
    }
}
