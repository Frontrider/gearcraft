class RecipeTypeEntry {

    val values= HashMap<String,String>()

    var children: ArrayList<RecipeTypeEntry> = ArrayList()

    fun addTag(name: String, value: String) {
        values[name] = value
    }

    fun addChild(entry: RecipeTypeEntry) {
        children.add(entry)
    }
}