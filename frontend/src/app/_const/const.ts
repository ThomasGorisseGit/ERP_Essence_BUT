import { Incident } from "../_interfaces/incident";

export const ApiURL:string = "http://localhost:8080";
export const INCIDENTS : Incident[] = [
  {
    id:0,
    title: "Alarme",
    description: "",
    date: new Date().toISOString().split("T")[0],
    image: "assets/incidents/alarm.png",
    solution:"En cas d'alarme, veuillez évacuer le bâtiement dans le calme, fermer les portes de résisance et rassembler les clients dans les zones prévues à cet effet. Les secours sont immédiatement prévenus"
  },
  {
    id:0,
    title: "Danger",
    description: "",
    date: new Date().toISOString().split("T")[0],
    image: "assets/incidents/warning.png",
    solution:"En cas de danger, veuillez placer un panneau de signalisation sur la zone de danger dans le but de prévenir les clients."

  },
  {
    id:0,
    title: "Gros danger",
    description: "",
    date: new Date().toISOString().split("T")[0],
    image: "assets/incidents/big_warning.png",
  },
  {
    id:0,
    title: "Erreur",
    description: "",
    date: new Date().toISOString().split("T")[0],
    image: "assets/incidents/error.png",
  },
  {
    id:0,
    title: "Rapport",
    description: "",
    date: new Date().toISOString().split("T")[0],
    image: "assets/incidents/report.png",
    solution:"En cas de rapport d'erreur, veuillez résoudre les conflits en vous référent au mannuel d'utilisation. Transmettez ensuite un rapport fonctionnel montrant la résolution des conflits"

  },
  {
    id:0,
    title: "Maintenance",
    description: "",
    date: new Date().toISOString().split("T")[0],
    image: "assets/incidents/maintenance.png",
  },
  {
    id:0,
    title: "Toxique",
    description: "",
    date: new Date().toISOString().split("T")[0],
    image: "assets/incidents/toxic.png",
    solution:"En cas de détection de produits toxiques, veuillez évacuer le bâtiement dans le calme, fermer les portes de résisance et rassembler les clients dans les zones prévues à cet effet. Les secours sont immédiatement prévenus"

  },
  {
    id:0,
    title: "Colis suspect",
    description: "",
    date: new Date().toISOString().split("T")[0],
    image: "assets/incidents/suspect.png",
  }
]
