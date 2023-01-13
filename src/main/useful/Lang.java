package main.useful;

import main.Controller;

// TODO: CHANGE lang TO ENUM
// TODO: OPTIMIZE IT
public class Lang {

    public static String getEquiv(String s) {
        String lang = Controller.lang;

        if (lang == null || lang.equals("french")) return s;

        switch (s) {
            case "Modifier":
                switch (lang) {
                    case "arabic":
                        return "تعديل";
                    case "english":
                        return "Edit";
                }
                break;

            case "Retourner":
                switch (lang) {
                    case "arabic":
                        return "رجوع";
                    case "english":
                        return "Return";
                }
                break;

            case "Tous les champs doivent être remplis.":
                switch (lang) {
                    case "arabic":
                        return "يجب ملأ جميع المجالات.";
                    case "english":
                        return "All Fields Must be Filled.";
                }
                break;

            case "Numéro de CIN invalide.":
                switch (lang) {
                    case "arabic":
                        return "رقم بطاقة التعريف غير صحيح.";
                    case "english":
                        return "Invalid ID Card Number.";
                }
                break;

            case "La classe saisie n'existe pas.":
                switch (lang) {
                    case "arabic":
                        return "القسم غيم موجود.";
                    case "english":
                        return "Entered Class Does Not Exist.";
                }
                break;

            case "Ajouté avec succès.":
                switch (lang) {
                    case "arabic":
                        return "تمّت الإضافة بنجاح.";
                    case "english":
                        return "Added Successfully.";
                }
                break;

            case "Erreur lors de l'ajout.":
                switch (lang) {
                    case "arabic":
                        return "لم تتم الإضافة.";
                    case "english":
                        return "Error While Attempting to Add.";
                }
                break;

            case "Enregistrer":
                switch (lang) {
                    case "arabic":
                        return "تسجيل";
                    case "english":
                        return "Save";
                }
                break;

            case "Annuler":
                switch (lang) {
                    case "arabic":
                        return "إلغاء";
                    case "english":
                        return "Undo";
                }
                break;

            case "Modifié avec succès.":
                switch (lang) {
                    case "arabic":
                        return "تمّ التعديل بنجاح.";
                    case "english":
                        return "Edited Successfully.";
                }
                break;

            case "Erreur lors de la modification.":
                switch (lang) {
                    case "arabic":
                        return "لم تتم عمليّة التعديل.";
                    case "english":
                        return "Error While Attempting to Modify.";
                }
                break;

            case "Supprimer Étudiant":
                switch (lang) {
                    case "arabic":
                        return "حذف طالب";
                    case "english":
                        return "Delete Student";
                }
                break;

            case "Voulez-vous vraiment supprimer cet étudiant ?":
                switch (lang) {
                    case "arabic":
                        return "هل أنت متأكّد أنّك تريد حذف هذا الطالب ؟";
                    case "english":
                        return "Are you sure you want to delete this student ?";
                }
                break;

            case "Voulez-vous vraiment quitter ?":
                switch (lang) {
                    case "arabic":
                        return "هل أنت متأكّد أنّك تريد المغادرة ؟";
                    case "english":
                        return "Are you sure you want to quit ?";
                }
                break;

            case "Supprimé avec succès.":
                switch (lang) {
                    case "arabic":
                        return "تمّ الحذف بنجاح.";
                    case "english":
                        return "Deleted Successfully.";
                }
                break;

            case "Erreur lors de la suppression.":
                switch (lang) {
                    case "arabic":
                        return "لم تتم عمليّة الحذف.";
                    case "english":
                        return "Error While Attempting to Delete.";
                }
                break;

            case "Aucune langue sélectionnée.":
                switch (lang) {
                    case "arabic":
                        return "لا يتم إختيار أي لغة.";
                    case "english":
                        return "No Selected Language.";
                }
                break;

            case "Langue enregistrée.":
                switch (lang) {
                    case "arabic":
                        return "تمّ تسجيل اللغة.";
                    case "english":
                        return "Language Saved.";
                }
                break;

            case "Erreur lors de l'enregistrement.":
                switch (lang) {
                    case "arabic":
                        return "لم يتم تسجيل اللغة.";
                    case "english":
                        return "Error While Attempting to Save.";
                }
                break;

            case "Bonjour !":
                switch (lang) {
                    case "arabic":
                        return "صباح الخير !";
                    case "english":
                        return "Good Morning !";
                }
                break;

            case "Bon après-midi !":
                switch (lang) {
                    case "arabic":
                        return "مساء الخير !";
                    case "english":
                        return "Good Afternoon !";
                }
                break;

            case "Bonsoir !":
                switch (lang) {
                    case "arabic":
                        return "مساء الخير !";
                    case "english":
                        return "Good Evening !";
                }
                break;

            case "Choisir Document":
                switch (lang) {
                    case "arabic":
                        return "إختيار وثيقة";
                    case "english":
                        return "Select Document";
                }
                break;

            case "Erreur lors de l'importation des documents.":
                switch (lang) {
                    case "arabic":
                        return "لم تتم عملية تحميل الوثائق.";
                    case "english":
                        return "No Document is Imported.";
                }
                break;

            case "Erreur lors de l'ajout des documents.":
                switch (lang) {
                    case "arabic":
                        return "لم تتم إضافة الوثائق.";
                    case "english":
                        return "Error While Attempting to Add Docuements.";
                }
                break;

            case " document ajouté avec succès.":
                switch (lang) {
                    case "arabic":
                        return " وثيقة أضيفة.";
                    case "english":
                        return " Document Added Successfully.";
                }
                break;

            case " documents ajoutés avec succès.":
                switch (lang) {
                    case "arabic":
                        return " وثائق أضيفة.";
                    case "english":
                        return " Documents Added Successfully.";
                }
                break;

            case "Aucun document.":
                switch (lang) {
                    case "arabic":
                        return "لا يوجد أي وثيقة.";
                    case "english":
                        return "No Document.";
                }
                break;

            case "Supprimer Tous Documents":
                switch (lang) {
                    case "arabic":
                        return "حذف جميع الوثائق.";
                    case "english":
                        return "Delete All Documents.";
                }
                break;

            case "Voulez-vous vraiment supprimer tous les documents de cet étudiant ?":
                switch (lang) {
                    case "arabic":
                        return "هل أنت متأكّد أنّك تريد حذف كل وثائق هذا الطالب ؟";
                    case "english":
                        return "Are you sure you want to delete all documents of this student ?";
                }
                break;

            case "Tous les documents sont supprimés avec succès.":
                switch (lang) {
                    case "arabic":
                        return "تمّ حذف كلّ الوثائق.";
                    case "english":
                        return "All Documents Deleted Successfully.";
                }
                break;

            case "Erreur lors de la suppression de tous les documents.":
                switch (lang) {
                    case "arabic":
                        return "لم يتم حذف كل الوثائق.";
                    case "english":
                        return "Error While Attempting to Delete All Documents.";
                }
                break;

            case "Erreur lors de l'ouverture du document.":
                switch (lang) {
                    case "arabic":
                        return "لم تتم عمليّة فتح الوثيقة.";
                    case "english":
                        return "Error While Attempting to Open Document.";
                }
                break;

            case "Supprimer Document":
                switch (lang) {
                    case "arabic":
                        return "حذف وثيقة";
                    case "english":
                        return "Delete Document";
                }
                break;

            case "Voulez-vous vraiment supprimer cet document ?":
                switch (lang) {
                    case "arabic":
                        return "هل أنت متأكّد أنّك تريد حذف هذه الوثيقة ؟";
                    case "english":
                        return "Are you sure you want to delete this document ?";
                }
                break;

            case "1 document supprimé avec succès.":
                switch (lang) {
                    case "arabic":
                        return "1 وثيقة حذفت بنجاح.";
                    case "english":
                        return "1 Document Deleted Succssfully.";
                }
                break;

            case "Erreur lors de la suppression du document.":
                switch (lang) {
                    case "arabic":
                        return "لم تتم عمليّة حذف الوثيقة.";
                    case "english":
                        return "Error While Attempting to Delete Document.";
                }
                break;

            case "Entrer un numéro de CIN d'abord.":
                switch (lang) {
                    case "arabic":
                        return "أدخل رقم بطاقة التعريف أوّلا.";
                    case "english":
                        return "Enter ID Card Number First.";
                }
                break;

            case "Aucun étudiant enregistré avec ce numéro de CIN.":
                switch (lang) {
                    case "arabic":
                        return "لا يوجد أي طالب مسجّل برقم بطاقة التعريف هذه.";
                    case "english":
                        return "No Student Registered with ID Card Number.";
                }
                break;

            case "Oui":
                switch (lang) {
                    case "arabic":
                        return "نعم";
                    case "english":
                        return "Yes";
                }
                break;

            case "Non":
                switch (lang) {
                    case "arabic":
                        return "لا";
                    case "english":
                        return "No";
                }
                break;

            case "Aucune classe selectionnée.":
                switch (lang) {
                    case "arabic":
                        return "لم يتم إختيار أي قسم.";
                    case "english":
                        return "No Selected Class.";
                }
                break;

            case "Erreur lors de la recherche.":
                switch (lang) {
                    case "arabic":
                        return "لم تتم عمليّة البحث بنجاح.";
                    case "english":
                        return "Error While Attempting to Search.";
                }
                break;

            case "Entrer un numéro de CIN valide.":
                switch (lang) {
                    case "arabic":
                        return "أدخل رقم بطاقة تعريف صحيح.";
                    case "english":
                        return "Enter a Valid ID Card Number.";
                }
                break;

            case "Aucun fichier selectionné.":
                switch (lang) {
                    case "arabic":
                        return "لم يتم إختيار أي ملف.";
                    case "english":
                        return "No File is Selected.";
                }
                break;

            case "Connexion échouée":
                switch (lang) {
                    case "arabic":
                        return "لم يتم الإتّصال";
                    case "english":
                        return "ُConnection Failed";
                }
                break;

            case "La connexion à la base de données a échoué.":
                switch (lang) {
                    case "arabic":
                        return "لم يتم الإتّصال بقاعدة البيانات.";
                    case "english":
                        return "Cannot Connect to Database.";
                }
                break;

            case "Quitter":
                switch (lang) {
                    case "arabic":
                        return "الخروج";
                    case "english":
                        return "Quit";
                }
                break;

            case "SG des Relevés de Notes":
                switch (lang) {
                    case "arabic":
                        return "نظام إدارة بطاقات الأعداد";
                    case "english":
                        return "Academic Transcripts MS";
                }
                break;

            // PANE 07
            case "Aucun étudiant.":
                switch (lang) {
                    case "arabic":
                        return "لا يوجد أي طالب.";
                    case "english":
                        return "No Student.";
                }
                break;

            case "Étudiant":
                switch (lang) {
                    case "arabic":
                        return "الطالب";
                    case "english":
                        return "Student";
                }
                break;

            case "Sélectionner une classe.":
                switch (lang) {
                    case "arabic":
                        return "إختر قسم.";
                    case "english":
                        return "Select Class.";
                }
                break;

            case "Aucune classe trouvée.":
                switch (lang) {
                    case "arabic":
                        return "لا يوجد أي قسم.";
                    case "english":
                        return "No Class Found.";
                }
                break;

            case "Supprimer Classe":
                switch (lang) {
                    case "arabic":
                        return "حذف قسم";
                    case "english":
                        return "Delete Class";
                }
                break;

            case "Voulez-vous vraiment supprimer cette classe avec tous ses données ?":
                switch (lang) {
                    case "arabic":
                        return "هل أنت متأكّد أنّك تريد حذف هذا القسم بجميع معطياته ؟";
                    case "english":
                        return "Are you sure you want to delete this class with all of its data ?";
                }
                break;

            case "Entrer le nom de la classe d'abord.":
                switch (lang) {
                    case "arabic":
                        return "أدخل إسم القسم أوّلا.";
                    case "english":
                        return "Enter Class Name First.";
                }
                break;
            case "Sélectionner boursier ou non.":
                switch (lang) {
                    case "arabic":
                        return "إختر متحصّل على منحة أم لا.";
                    case "english":
                        return "Select Has Scholarship or Not.";
                }
            case "Aucune condition selectionnée.":
                switch (lang) {
                    case "arabic":
                        return "لم يتم إختيار أي حالة.";
                    case "english":
                        return "No Selected Condition.";
                }
            case "Réussi":
                switch (lang) {
                    case "arabic":
                        return "ناجح";
                    case "english":
                        return "Succeded";
                }
            case "Débranché":
                switch (lang) {
                    case "arabic":
                        return "منقطع";
                    case "english":
                        return "Dropout";
                }
            case "Enregistrement retiré":
                switch (lang) {
                    case "arabic":
                        return "سحب تسجيل";
                    case "english":
                        return "Record Withdrawn";
                }
            case "Parti":
                switch (lang) {
                    case "arabic":
                        return "مغادر";
                    case "english":
                        return "Departed";
                }
            case "Il existe un étudiant avec la même CIN. Voulez-vous modifier ses informations ?":
                switch (lang) {
                    case "arabic":
                        return "يوجد طالب مسجّل بنفس رقم بطاقة التعريف هذه. هل تريد تعديل معطياته ؟";
                    case "english":
                        return "A student with the same ID card number already exists. Do you want to modify their details ?";
                }
            case "CIN Existante":
                switch (lang) {
                    case "arabic":
                        return "رقم بطاقة تعريف مسجّل";
                    case "english":
                        return "ID Card Number Exists";
                }
            case "Il existe un étudiant avec la même CIN.":
                switch (lang) {
                    case "arabic":
                        return "يوجد طالب مسجّل بنفس رقم بطاقة التعريف هذه.";
                    case "english":
                        return "A Student With Same ID Card Number Already Exists.";
                }
            case "Cliquer sur Valider pour modifier les informations de l'étudiant.":
                switch (lang) {
                    case "arabic":
                        return "إضغط على بحث لتعديل معطيات الطالب.";
                    case "english":
                        return "Click on Validate to Edit Student's Data.";
                }
        }

        return s;
    }
}