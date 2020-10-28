package training.android.plannin_cuisine.Entities

import android.os.Parcel
import android.os.Parcelable
import training.android.plannin_cuisine.Database.App
import java.io.Serializable

class Recipe(val name: String, val description: String, val recipeIngredientId: Int, val id: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(recipeIngredientId)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}

val crepe = Recipe(
    "Recette de crêpe", "" +
            "1. Mettre la farine dans une terrine et former un puits.\n " +
            "2. Y déposer les oeufs entiers, le sucre, l'huile et le beurre.\n " +
            "3.Mélanger délicatement avec un fouet en ajoutant au fur et à mesure le lait. La pâte ainsi obtenue doit avoir une consistance d'un liquide légèrement épais.\n " +
            "4.Parfumer de rhum.\n " +
            "5.Faire chauffer une poêle antiadhésive et la huiler très légèrement. Y verser une louche de pâte, la répartir dans la poêle puis attendre qu'elle soit cuite d'un côté avant de la retourner. Cuire ainsi toutes les crêpes à feu doux.\n "
, 1, 1)

val risotto = Recipe(
    "Risotto",
    "1. Il existe sur la toile deux écoles du risotto aux champignons, à savoir celle du champignon qui cuit dans le riz, et celle des champignons poêlés ajoutés à la dernière minute. Ayant longtemps pratiqué les deux recettes, je souhaitais vous communiquer la mienne :\n" +
            "2. Séparer les champignons en deux (préférer des cèpes) : une partie servira à élaborer le bouillon et cuira avec le riz. L'autre partie sera poêlé au dernier moment pour la présentation et mettre en avant le champignon tout en conservant une texture ferme.\n" +
            "Emincer un petit peu d'ail, d'échalote et de persil séparément et réserver.\n" +
            "3. Nettoyer les champignons et les couper en morceaux.\n" +
            "4. Faire blondir dans une casserole un demi-oignon émincé dans un mélange de beurre et d'huile d'olive.\n" +
            "5. Ajouter et poêler les champignons.\n" +
            "6. Ajouter du bouillon de légume ou de poule (assez pour nourrir le risotto).\n" +
            "7. Laisser mijoter le bouillon pour qu'il s'imprègne bien du goût et des saveurs des champignons.\n" +
            "8. Chauffer une nouvelle casserole (qui accueillera le risotto), ajouter un peu d'huile d'olive et de beurre que vous ferez blondir.\n" +
            "9. Ajouter le riz carnaroli et remuer jusqu'à le rendre translucide (attention à la température, le riz ne doit pas coller).\n" +
            "10. Déglacer avec un verre de vin blanc sec. Continuer à remuer pour que le riz n'adhère pas et laisser réduire l'alcool.\n" +
            "11. Verser une louche de bouillon que vous avez laissé mijoter et continuer à remuer constamment jusqu'à l'absorption totale du riz.\n" +
            "12. Réitérer l'étape 11 jusqu'au point de cuisson (préférer al dente).\n" +
            "13. Juste avant la fin, faire fondre du beurre et un peu d'huile d'olive dans une poêle tout en remuant le risotto.\n" +
            "14. Faire blondir l'échalote émincée dans la poêle puis ajouter l'ail (attention à ne pas le brûler).\n" +
            "15. Faire poêler les champignons tout en remuant le risotto, assaisonner et ajouter le persil émincé en fin de cuisson avec un tour de moulin à poivre puis réserver.\n" +
            "16. Assaisonner le risotto juste avant la fin.\n" +
            "17. Ciseler du persil et en parsemer au dessus.\n" +
            "18. Dresser le riz dans des assiettes creuses. Placer soigneusement sur le haut les champignons poêlés.\n" +
            "19. Servir avec un tour de moulin à poivre et du parmesan.\n" +
            "20. Le gras du beurre va s'opposer au salé du parmesan et à l'amertume du cèpe en arrière plan. Les puristes privilégieront un rouge italien d'âge moyen avec une structure légère mais à la fois ronde et consistante. Personnellement, je préfère un blanc sec minéral un peu évolué au accent de sous-bois. Un bourgogne fera parfaitement l'affaire. Bon appétit\n"
, 2, 2)

val miniBurger = Recipe(
    "Mini burgers", "\n" +
            "1. Dans un saladier, mélangez le Haché de veau Façon Bouchère Tendriade avec le sel, le poivre et le paprika.\n" +
            "2. Formez des boules de 40g environ et les aplatir légèrement avec la paume de la main.\n" +
            "Si besoin, utilisez un emporte-pièce pour détailler le haché de veau de manière uniforme. Réservez au frais.\n" +
            "3. Pelez et émincez finement les oignons rouges.\n" +
            "Faites les revenir dans une poêle avec l’huile d’olive, jusqu’à ce qu’ils deviennent translucides.\n" +
            "4. Lorsque les oignons commencent à colorer, ajoutez le sucre et le vinaigre balsamique.\n" +
            "Poursuivez la cuisson encore 5 à 7 minutes en mélangeant régulièrement afin de caraméliser les oignons.\n" +
            "5. Pendant ce temps, nettoyez et coupez les champignons en lamelles.\n" +
            "Faites les revenir à la poêle avec un filet d’huile d’olive pendant 5 minutes.\n" +
            "6. Réservez les champignons, récupérez la poêle et faites cuire la viande.\n" +
            "Saisir cette dernière à feu vif de chaque côté, puis finir la cuisson des steaks à feu doux.\n" +
            "7. Pendant ce temps, chauffez les pains à burger au grille-pain.\n" +
            "8. Puis passez au montage des minis burgers. Étalez une couche de ketchup sur le pain, déposez ensuite une cuillère d’oignons caramélisés, une ou deux rondelles de cornichon, le steak de veau, le comté, une cuillère de champignons et la salade.\n" +
            "Refermez le burger et dégustez.\n"
,3, 3)

val pancakes = Recipe(
    "Pancakes",
    "1. Faire fondre le beurre, dans une casserole à feu doux ou dans un bol au micro-ondes.\n" +
            "Mettre la farine, la levure et le sucre dans un saladier. Mélanger et creuser un puits.\n" +
            " Ajouter ensuite les oeufs entiers et fouetter l'ensemble.\n" +
            "Incorporer le beurre fondu, fouetter puis délayer progressivement le mélange avec le lait afin d'éviter les grumeaux.\n" +
            "Laisser reposer la pâte au minimum 1 heure au réfrigérateur.\n" +
            "Dans une poêle chaude et légèrement huilée, faire cuire comme des crêpes, mais en les faisant plus petites. Réserver au chaud et déguster. "
,4, 4)

val pouletCocoReunionnais = Recipe("Poulet coco réunionnais",
        "1. Dans une cocotte, faire dorer les morceaux de poulet dans l'huile d'olive. Saler, poivrer.\n" +
        "2. Ajouter de l'eau et laisser cuire une demi-heure.\n" +
        "3. Couper l'oignon et le poivron en morceaux, les mettre dans la cocotte, ainsi que le lait de coco et le coulis de tomate. Laisser réduire une demi-heure environ. C'est prêt.\n", 5, 5)





fun createAppRecipies() {

    App.database.createRecipe(crepe)
    App.database.createRecipe(risotto)
    App.database.createRecipe(miniBurger)
    App.database.createRecipe(pancakes)
    App.database.createRecipe(pouletCocoReunionnais)

}