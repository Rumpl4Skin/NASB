package by.ssrlab.NASB.ui.engineering


import by.krokam.biarescie.ui.recycler.SectionListAdapter
import by.ssrlab.NASB.ListVM
import by.ssrlab.NASB.R
import by.ssrlab.NASB.data.items.Section
import by.ssrlab.tibo2019.util.addTo
import by.ssrlab.tibo2019.util.executeInBackSubOnMain


class EngineeringViewModel : ListVM<Section>() {

    override val adapter = SectionListAdapter().apply {
        onClick = {
            repo?.selectedSectionID = it.id
            nav?.navigate(R.id.exhibitListFragment)
        }
    }

    override fun onCreate() {
        repo?.sections?.executeInBackSubOnMain()?.subscribe {
            adapter.items = it!!.sortedBy { it.id.toInt() }
            isLoading.value = false

        }?.addTo(subs)
    }
}